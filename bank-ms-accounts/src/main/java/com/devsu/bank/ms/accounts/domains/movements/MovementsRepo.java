package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.commons.AbstractEntityRepo;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;

import java.util.stream.Stream;

public interface MovementsRepo extends AbstractEntityRepo<Movement>, CustomMovementsRepo {

    Stream<Movement> findAllByAccount_Number(String accountNumber);

}
