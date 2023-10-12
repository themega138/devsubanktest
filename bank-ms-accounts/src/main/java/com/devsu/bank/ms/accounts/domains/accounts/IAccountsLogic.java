package com.devsu.bank.ms.accounts.domains.accounts;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;
import com.devsu.bank.ms.accounts.domains.commons.ICrudLogic;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementItemResponse;

import java.util.List;

public interface IAccountsLogic extends ICrudLogic<AccountDTO> {
    MovementDTO createAccountMovement(String accountNumber, MovementDTO dto);

    List<MovementDTO> getAccountMovement(String accountNumber);
}
