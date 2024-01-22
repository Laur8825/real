package com.realestate.propertylisting.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {

    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title length must be between 3 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description length must not exceed 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;
}