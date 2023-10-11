package com.devsu.bank.ms.clients.domains.client.models;

import com.devsu.bank.ms.clients.domains.commons.models.Person;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends Person {

    private String clientId;
    private Boolean state;
    private String password;

}
