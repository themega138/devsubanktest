package com.devsu.bank.ms.clients.domains.client.models;

import lombok.Builder;

@Builder
public record ClientDetailResponse(
        Long id,
        String uuid,
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
