package com.devsu.bank.ms.accounts.domains.accounts;

import com.devsu.bank.ms.accounts.domains.accounts.models.Account;
import com.devsu.bank.ms.accounts.domains.commons.AbstractEntityRepo;

import java.util.Optional;

public interface AccountsRepo extends AbstractEntityRepo<Account> {

    Optional<Account> findByNumber(String number);

}
