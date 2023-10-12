package com.devsu.bank.ms.accounts.domains.movements.models;

import com.devsu.bank.ms.accounts.domains.accounts.models.Account;
import com.devsu.bank.ms.accounts.domains.commons.models.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movement extends AbstractEntity {

    @ManyToOne
    private Account account;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private MovementType type;
    private Boolean state;
}
