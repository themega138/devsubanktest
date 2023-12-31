package com.devsu.bank.ms.accounts.domains.movements.models;

import java.math.BigDecimal;

public record MovementUpdateRequest(
        BigDecimal amount,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        MovementType type,
        Boolean state
) {
}
