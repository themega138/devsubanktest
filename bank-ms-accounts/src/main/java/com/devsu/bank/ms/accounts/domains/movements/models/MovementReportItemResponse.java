package com.devsu.bank.ms.accounts.domains.movements.models;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementReportItemResponse(
        String movementUid,
        LocalDateTime date,
        String accountNumber,
        AccountType accountType,
        MovementType movementType,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        Boolean state,
        BigDecimal movementAmount

) {
}
