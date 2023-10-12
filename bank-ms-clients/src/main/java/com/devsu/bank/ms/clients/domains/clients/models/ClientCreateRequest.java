package com.devsu.bank.ms.clients.domains.clients.models;

public record ClientCreateRequest(
        String name,
        String gender,
        Integer age,
        String personalId,
        String address,
        String phone,
        String password,
        Boolean state
) {
}
