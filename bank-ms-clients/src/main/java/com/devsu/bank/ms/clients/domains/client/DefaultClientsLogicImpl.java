package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.clients.domains.commons.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultClientsLogicImpl extends DefaultAbstractCrudLogic<ClientDTO, Client> implements IClientsLogic {

    private final PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final ClientsMapper mapper;

    public DefaultClientsLogicImpl(
            ClientsRepository clientsRepository, ClientsMapper mapper
    ) {
        super(clientsRepository, mapper);
        this.mapper = mapper;
    }

    @Override
    public ClientDTO createOne(ClientDTO dto) {
        ClientDTO data = this.mapper.updateDTOPassword(dto, passwordEncoder.encode(dto.password()));
        return super.createOne(data);
    }
}
