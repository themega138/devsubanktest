package com.devsu.bank.ms.accounts.domains.movements.models;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MovementDTO {
    private Long id;
    private String uid;
    private AccountDTO account;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private MovementType type;
    private Boolean state;
}
