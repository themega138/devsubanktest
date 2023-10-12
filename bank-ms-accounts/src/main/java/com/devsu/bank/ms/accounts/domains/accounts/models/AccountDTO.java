package com.devsu.bank.ms.accounts.domains.accounts.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDTO{
    private Long id;
    private String uid;
    private ClientDTO client;
    private String number;
    private BigDecimal balance;
    private Boolean state;
    private AccountType type;
}
