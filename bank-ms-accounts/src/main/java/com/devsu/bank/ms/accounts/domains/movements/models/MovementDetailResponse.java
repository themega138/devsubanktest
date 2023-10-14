package com.devsu.bank.ms.accounts.domains.movements.models;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDetailResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record MovementDetailResponse(
        Long id,
        String uid,
        AccountDetailResponse account,
        LocalDateTime date,
        BigDecimal amount,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        String type,
        Boolean state
) {
}
