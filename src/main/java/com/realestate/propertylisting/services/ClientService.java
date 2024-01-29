package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDTO saveClient(ClientDTO clientDTO);

    Optional<ClientDTO> getClientById(Long id);

    List<ClientDTO> getAllClients();

    void deleteClient(Long id);
}