package com.realestate.propertylisting.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Assuming Employees can also be linked to Properties
    @ManyToMany(mappedBy = "employees", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<Property> properties = new HashSet<>();
}
