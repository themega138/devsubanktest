package com.devsu.bank.ms.accounts.domains.accounts.models;

import com.devsu.bank.ms.accounts.domains.movements.models.MovementType;

import java.math.BigDecimal;

public record MovementCreateRequest(
        BigDecimal amount,
        MovementType type,
        Boolean state

) {
}
