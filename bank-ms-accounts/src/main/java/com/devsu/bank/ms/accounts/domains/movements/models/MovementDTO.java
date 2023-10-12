package com.devsu.bank.ms.accounts.domains.movements.models;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementDTO(
        Long id,
        String uid,
        AccountDTO account,
        LocalDateTime date,
        BigDecimal amount,
        BigDecimal currentBalance,
        MovementType type,
        Boolean state
) {
}
