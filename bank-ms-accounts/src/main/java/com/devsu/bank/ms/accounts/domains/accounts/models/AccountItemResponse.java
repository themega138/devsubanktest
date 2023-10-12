package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountItemResponse(
        Long id,
        String uid,
        ClientDetailResponse client,
        String number,
        BigDecimal balance,
        Boolean state
) {
}
