package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.ClientDTO;
import com.realestate.propertylisting.models.entities.Client;
import com.realestate.propertylisting.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private ClientDTO convertToDTO(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail()
        );
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}
