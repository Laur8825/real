package integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realestate.propertylisting.controllers.PropertyController;
import com.realestate.propertylisting.models.dtos.PropertyDTO;
import com.realestate.propertylisting.services.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PropertyController.class)
public class PropertyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    private PropertyDTO propertyDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        propertyDTO = new PropertyDTO(1L, "Test Property", "Description", 100000.0);
    }

    @Test
    void whenPostProperty_thenCreateProperty() throws Exception {
        ArgumentCaptor<PropertyDTO> captor = ArgumentCaptor.forClass(PropertyDTO.class);
        given(propertyService.saveProperty(captor.capture())).willReturn(propertyDTO);

        mockMvc.perform(post("/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propertyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(propertyDTO.getTitle())));
    }

    @Test
    void whenGetPropertyById_thenPropertyReturned() throws Exception {
        given(propertyService.getPropertyById(anyLong())).willReturn(Optional.of(propertyDTO));

        mockMvc.perform(get("/properties/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(propertyDTO.getTitle())));
    }

    @Test
    void whenGetAllProperties_thenPropertiesReturned() throws Exception {
        given(propertyService.getAllProperties()).willReturn(Arrays.asList(propertyDTO));

        mockMvc.perform(get("/properties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(propertyDTO.getTitle())));
    }

    @Test
    void whenUpdateProperty_thenUpdatedPropertyReturned() throws Exception {
        given(propertyService.saveProperty(any(PropertyDTO.class))).willReturn(propertyDTO);

        mockMvc.perform(put("/properties/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propertyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(propertyDTO.getTitle())));
    }

    @Test
    void whenDeleteProperty_thenStatusOk() throws Exception {
        doNothing().when(propertyService).deleteProperty(anyLong());

        mockMvc.perform(delete("/properties/{id}", 1L))
                .andExpect(status().isOk());
    }
}
