package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.clients.domains.commons.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DefaultClientsLogicImpl extends DefaultAbstractCrudLogic<ClientDTO, Client> implements IClientsLogic {

    private final ClientsRepository clientsRepository;
    private final ClientsMapper mapper;

    public DefaultClientsLogicImpl(
            ClientsRepository clientsRepository, ClientsMapper mapper
    ) {
        super(clientsRepository, mapper);
        this.clientsRepository = clientsRepository;
        this.mapper = mapper;
    }
}
