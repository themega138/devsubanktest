package com.devsu.bank.ms.clients.domains.clients.models;

import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientDTO(
        Long id,
        UUID uid,
        String name,
        Gender gender,
        Integer age,
        String personalId,
        String address,
        String phone,
        String password,
        Boolean state
) {
}
