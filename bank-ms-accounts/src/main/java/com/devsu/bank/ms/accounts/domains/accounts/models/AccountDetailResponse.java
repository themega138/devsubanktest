package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDetailResponse(
        Long id,
        String uid,
        String clientId,
        String number,
        BigDecimal balance,
        Boolean state
) {
}
