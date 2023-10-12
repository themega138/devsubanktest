package com.devsu.bank.ms.clients.domains.clients.models;

public record ClientUpdateRequest(
        String name,
        String gender,
        Integer age,
        String personalId,
        String address,
        String phone,
        Boolean state
) {
}
