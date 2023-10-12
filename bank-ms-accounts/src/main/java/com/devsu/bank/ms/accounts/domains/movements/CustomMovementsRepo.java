package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.movements.models.Movement;

public interface CustomMovementsRepo {
    Movement createMovement(Movement movement);
}
