package com.realestate.propertylisting.controllers;

import com.realestate.propertylisting.models.dtos.PropertyDTO;
import com.realestate.propertylisting.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Create a new property
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO newProperty = propertyService.saveProperty(propertyDTO);
        return ResponseEntity.ok(newProperty);
    }

    // Get a property by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all properties
    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Update a property
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.saveProperty(propertyDTO));
    }

    // Delete a property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok().build();
    }
}