package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {

}
