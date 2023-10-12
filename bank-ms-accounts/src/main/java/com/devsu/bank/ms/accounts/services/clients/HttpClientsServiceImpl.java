package com.devsu.bank.ms.accounts.services.clients;

import com.devsu.bank.ms.accounts.config.properties.ClientServiceProperties;
import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class HttpClientsServiceImpl implements IClientsService {
    private final RestTemplate client;
    private final ClientServiceProperties properties;

    public HttpClientsServiceImpl(RestTemplate client, ClientServiceProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public ClientDTO getByClientId(String clientId) {
        URI url = URI.create(properties.getBaseUrl().concat("/%s".formatted(clientId)));
        return this.client.getForObject(url, ClientDTO.class);
    }
}
