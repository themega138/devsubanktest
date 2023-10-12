package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Builder;

@Builder
public record ClientDetailResponse(
        Long id,
        String uid,
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
