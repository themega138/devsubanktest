package com.devsu.bank.ms.accounts.domains.movements.models;

import java.math.BigDecimal;

public record MovementCreateRequest(

        String accountId,
        BigDecimal amount,
        BigDecimal currentBalance,
        String type,
        Boolean state
) {
}
