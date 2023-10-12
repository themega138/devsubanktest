package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDetailResponse(
        Long id,
        String uid,
        Long clientId,
        String clientUid,
        String number,
        BigDecimal balance,
        Boolean state,
        AccountType type
) {
}
