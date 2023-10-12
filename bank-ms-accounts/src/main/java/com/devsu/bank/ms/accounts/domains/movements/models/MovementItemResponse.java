package com.devsu.bank.ms.accounts.domains.movements.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MovementItemResponse(
        Long id,
        String uuid,
        String accountUid,
        String accountNumber,
        BigDecimal amount,
        BigDecimal currentBalance,
        MovementType type,
        Boolean state
) {
}
