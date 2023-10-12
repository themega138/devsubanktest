package com.devsu.bank.ms.accounts.domains.accounts.models;

import com.devsu.bank.ms.accounts.domains.commons.models.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {

    @Column(nullable = false)
    private Long clientId;
    @Column(nullable = false)
    private String clientUid;
    @Column(unique = true, nullable = false)
    private String number;
    private AccountType type;
    private BigDecimal balance;
    private Boolean state;
}
