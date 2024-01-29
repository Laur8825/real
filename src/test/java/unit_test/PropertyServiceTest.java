package unit_test;

import com.realestate.propertylisting.models.dtos.PropertyDTO;
import com.realestate.propertylisting.models.entities.Property;
import com.realestate.propertylisting.repositories.PropertyRepository;
import com.realestate.propertylisting.services.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    private Property property;
    private PropertyDTO propertyDTO;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(1L);
        property.setTitle("Test Property");
        property.setDescription("Description");
        property.setPrice(100000.0);
    }

    @Test
    void testSaveProperty() {
        when(propertyRepository.save(any(Property.class))).thenReturn(property);
        PropertyDTO savedProperty = propertyService.saveProperty(propertyDTO);

        assertNotNull(savedProperty);
        assertEquals(propertyDTO.getTitle(), savedProperty.getTitle());
    }

    @Test
    void testGetPropertyByIdFound() {
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(property));
        Optional<PropertyDTO> foundProperty = propertyService.getPropertyById(property.getId());

        assertTrue(foundProperty.isPresent());
        assertEquals(property.getTitle(), foundProperty.get().getTitle());
    }

    @Test
    void testGetPropertyByIdNotFound() {
        Long propertyId = 2L; // Some id that does not exist
        when(propertyRepository.findById(propertyId)).thenReturn(Optional.empty());

        Optional<PropertyDTO> foundProperty = propertyService.getPropertyById(propertyId);

        assertFalse(foundProperty.isPresent());
    }

    @Test
    void testGetAllProperties() {
        when(propertyRepository.findAll()).thenReturn(Arrays.asList(property));
        List<PropertyDTO> properties = propertyService.getAllProperties();

        assertFalse(properties.isEmpty());
        assertEquals(1, properties.size());
        assertEquals(property.getTitle(), properties.get(0).getTitle());
    }

    @Test
    void testDeleteProperty() {
        Long propertyId = property.getId();
        doNothing().when(propertyRepository).deleteById(propertyId);

        propertyService.deleteProperty(propertyId);

        verify(propertyRepository, times(1)).deleteById(propertyId);
    }
}

