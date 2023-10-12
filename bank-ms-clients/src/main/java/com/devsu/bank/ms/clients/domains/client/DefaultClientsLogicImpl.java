package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.clients.domains.commons.utils.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class DefaultClientsLogicImpl extends DefaultAbstractCrudLogic<ClientDTO, Client> implements IClientsLogic {

    private final ClientsMapper mapper;

    public DefaultClientsLogicImpl(
            ClientsRepository clientsRepository, ClientsMapper mapper
    ) {
        super(clientsRepository, mapper);
        this.mapper = mapper;
    }

    @Override
    public ClientDTO createOne(ClientDTO dto) {
        ClientDTO data = this.mapper.updateDTOPassword(dto, hashPassword(dto.password()));
        return super.createOne(data);
    }

    private static String hashPassword(String pass) {
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(pass, salt);
        return hash;
    }
}
