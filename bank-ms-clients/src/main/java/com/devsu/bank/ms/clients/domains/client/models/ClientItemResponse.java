package com.devsu.bank.ms.clients.domains.client.models;

import lombok.Builder;

@Builder
public record ClientItemResponse (
        Long id,
        String uid,
        String name,
        String gender,
        Integer age,
        String personalId,
        String address,
        String phone,
        Boolean state
) {
}
