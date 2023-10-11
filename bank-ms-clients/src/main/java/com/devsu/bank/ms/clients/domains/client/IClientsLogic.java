package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.client.models.ClientDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClientsLogic {

    Page<ClientDTO> paginateClients(Pageable page);

    Optional<ClientDTO> getClient(Long id);

    ClientDTO createClient(ClientDTO clientDTO);
}
