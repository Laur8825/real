package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.PropertyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
@Service
>>>>>>> origin/developer
public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    Optional<PropertyDTO> getPropertyById(Long id);

    List<PropertyDTO> getAllProperties();

    void deleteProperty(Long id);
}