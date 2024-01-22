package com.realestate.propertylisting.repositories;

import com.realestate.propertylisting.models.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Find properties by title
    List<Property> findByTitleContaining(String title);

    // Find properties within a certain price range
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
}