package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.PropertyDTO;
import com.realestate.propertylisting.models.entities.Property;
import com.realestate.propertylisting.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Property property = convertToEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);
        return convertToDTO(savedProperty);
    }

    @Override
    public Optional<PropertyDTO> getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    private PropertyDTO convertToDTO(Property property) {
        if (property == null) {
            return null;
        }
        return new PropertyDTO(property.getId(), property.getTitle(), property.getDescription(), property.getPrice());
    }

    private Property convertToEntity(PropertyDTO propertyDTO) {
        if (propertyDTO == null) {
            return null;
        }
        Property property = new Property();
        property.setId(propertyDTO.getId());
        property.setTitle(propertyDTO.getTitle());
        property.setDescription(propertyDTO.getDescription());
        property.setPrice(propertyDTO.getPrice());

        return property;
    }
}