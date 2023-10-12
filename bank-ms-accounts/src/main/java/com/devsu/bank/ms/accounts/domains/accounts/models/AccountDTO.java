package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Data;

import java.math.BigDecimal;

public record AccountDTO(
        Long id,
        String uid,
        String clientId,
        ClientDTO client,
        String number,
        BigDecimal balance,
        Boolean state
) {
}
