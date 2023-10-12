package com.devsu.bank.ms.accounts.services.clients;

import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;

public interface IClientsService {
    ClientDTO getByClientId(String clientId);
}
