package com.devsu.bank.ms.clients.domains.client.models;

public record ClientCreateRequest(
        String name,
        String gender,
        Integer age,
        String personalId,
        String address,
        String phone,
        String clientId,
        Boolean state
) {
}
