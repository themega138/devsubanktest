package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.commons.AbstractEntityRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends AbstractEntityRepo<Client> {

}
