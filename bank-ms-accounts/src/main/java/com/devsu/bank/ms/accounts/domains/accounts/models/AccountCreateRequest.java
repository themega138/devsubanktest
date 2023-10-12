package com.devsu.bank.ms.accounts.domains.accounts.models;

import java.math.BigDecimal;

public record AccountCreateRequest(
        String clientId,
        String number,
        BigDecimal balance,
        Boolean state
) {
}
