package com.devsu.bank.ms.accounts.domains.accounts.models;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        String number,
        BigDecimal balance,
        Boolean state
) {
}
