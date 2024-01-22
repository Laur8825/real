package com.realestate.propertylisting.repositories;

import com.realestate.propertylisting.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Find clients by name
    List<Client> findByNameContaining(String name);

    // Find a client by email
    Optional<Client> findByEmail(String email);
}