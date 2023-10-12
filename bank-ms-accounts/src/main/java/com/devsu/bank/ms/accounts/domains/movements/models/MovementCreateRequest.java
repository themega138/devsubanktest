package com.devsu.bank.ms.accounts.domains.movements.models;

import java.math.BigDecimal;

public record MovementCreateRequest(

        Long accountId,
        BigDecimal amount,
        String type,
        Boolean state
) {
}
