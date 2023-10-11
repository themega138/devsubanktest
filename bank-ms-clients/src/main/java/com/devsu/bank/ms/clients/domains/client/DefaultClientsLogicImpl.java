package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DefaultClientsLogicImpl implements IClientsLogic {

    private final ClientsRepository clientsRepository;

    public DefaultClientsLogicImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public Page<ClientDTO> paginateClients(Pageable pageable) {
        return this.clientsRepository.findAll(pageable)
                .map(DefaultClientsLogicImpl::toClientDTO);
    }

    @Override
    public Optional<ClientDTO> getClient(Long id) {
        return this.clientsRepository.findById(id)
                .map(DefaultClientsLogicImpl::toClientDTO);
    }

    @Override
    public ClientDTO createClient(ClientDTO data) {
        Client result = this.clientsRepository.save(toCreateClient(data));
        return toClientDTO(result);
    }

    private Client toCreateClient(ClientDTO dto) {
        return Client.builder()
                .password(dto.password())
                .age(dto.age())
                .name(dto.name())
                .state(dto.state())
                .address(dto.address())
                .phone(dto.phone())
                .uuid(UUID.randomUUID())
                .clientId(UUID.randomUUID().toString())
                .build();
    }

    private static ClientDTO toClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .address(client.getAddress())
                .clientId(client.getClientId())
                .age(client.getAge())
                .phone(client.getPhone())
                .state(client.getState())
                .uuid(client.getUuid())
                .name(client.getName())
                .gender(client.getGender())
                .personalId(client.getPersonalId())
                .build();
    }
}
