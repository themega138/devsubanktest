package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.commons.IAbstractCrudLogic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientsLogic extends IAbstractCrudLogic<ClientDTO> {

}
