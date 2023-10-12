package com.devsu.bank.ms.accounts.domains.accounts;

import com.devsu.bank.ms.accounts.domains.accounts.models.Account;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;
import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import com.devsu.bank.ms.accounts.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.accounts.domains.movements.MovementsMapper;
import com.devsu.bank.ms.accounts.domains.movements.MovementsRepo;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.services.clients.IClientsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultAccountsLogicImpl extends DefaultAbstractCrudLogic<AccountDTO, Account> implements IAccountsLogic {

    private final AccountsMapper mapper;
    private final MovementsRepo movementsRepo;
    private final MovementsMapper movementsMapper;

    private final IClientsService clientsService;

    public DefaultAccountsLogicImpl(
            AccountsRepo accountsRepo,
            MovementsRepo movementsRepo,
            AccountsMapper mapper,
            MovementsMapper movementsMapper,
            IClientsService clientsService) {
        super(accountsRepo, mapper);
        this.movementsRepo = movementsRepo;
        this.mapper = mapper;
        this.movementsMapper = movementsMapper;
        this.clientsService = clientsService;
    }

    @Override
    public AccountDTO getOne(Long id) {
        AccountDTO account = super.getOne(id);
        ClientDTO client = this.clientsService.getByClientId(account.clientId());
        return this.mapper.updateDTO(account, client);
    }

    @Override
    public AccountDTO createOne(AccountDTO accountDTO) {
        this.clientsService.getByClientId(accountDTO.clientId());
        return super.createOne(accountDTO);
    }

    @Override
    public MovementDTO createAccountMovement(String accountNumber, MovementDTO dto) {
        Movement entity = this.movementsMapper.updateEntity(Movement.builder()
                .account(Account.builder()
                        .number(accountNumber)
                        .build())
                .build(), dto);

        return Optional.of(this.movementsRepo.createMovement(entity))
                .map(this.movementsMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Some error happened creating the movement..."));
    }

    @Override
    public List<MovementDTO> getAccountMovement(String accountNumber) {
        return this.movementsRepo.findAllByAccount_Number(accountNumber)
                .map(this.movementsMapper::toDTO)
                .toList();
    }
}
