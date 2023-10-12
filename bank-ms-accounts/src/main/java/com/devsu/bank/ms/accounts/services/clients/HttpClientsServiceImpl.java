package com.devsu.bank.ms.accounts.services.clients;

import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientsServiceImpl implements IClientsService {
    private final RestTemplate client;

    public HttpClientsServiceImpl(RestTemplate client) {
        this.client = client;
    }

    @Override
    public ClientDTO getByClientId(String clientId) {
        return null;
    }
}
