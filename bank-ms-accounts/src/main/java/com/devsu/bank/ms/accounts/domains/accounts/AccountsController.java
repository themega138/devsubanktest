package com.devsu.bank.ms.accounts.domains.accounts;

import com.devsu.bank.ms.accounts.domains.accounts.models.AccountCreateRequest;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDetailResponse;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountItemResponse;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountUpdateRequest;
import com.devsu.bank.ms.accounts.domains.accounts.models.MovementCreateRequest;
import com.devsu.bank.ms.accounts.domains.movements.MovementsMapper;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDetailResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementItemResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("v1/accounts")
public class AccountsController {

    private final IAccountsLogic logic;
    private final AccountsMapper mapper;

    private final MovementsMapper movementsMapper;

    public AccountsController(IAccountsLogic logic, AccountsMapper mapper, MovementsMapper movementsMapper) {
        this.logic = logic;
        this.mapper = mapper;
        this.movementsMapper = movementsMapper;
    }

    @GetMapping()
    public List<AccountItemResponse> paginateAccounts(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "20") final Integer size
    ) {
        return this.logic.paginate(PageRequest.of(pageNumber, size))
                .map(mapper::toItemResponse)
                .toList();
    }

    @GetMapping("{id}")
    public AccountDetailResponse getAccountDetail(
            @PathVariable Long id) {
        return mapper.toDetailResponse(this.logic.getOne(id));
    }

    @PostMapping()
    public AccountDetailResponse createAccount(
            @RequestBody() AccountCreateRequest data
    ) {
        AccountDTO result = this.logic.createOne(mapper.toDTO(data));
        return mapper.toDetailResponse(result);
    }

    @DeleteMapping("{id}")
    public void deleteClient(
            @PathVariable Long id) {
        this.logic.deleteOne(id);
    }

    @PutMapping("{id}")
    public void updateAccount(
            @PathVariable Long id,
            @RequestBody AccountUpdateRequest data
    ) {
        this.logic.updateOne(id, mapper.toDTO(data));
    }

    @PostMapping("{accountNumber}/movements")
    public MovementDetailResponse createAccountMovement(
            @PathVariable String accountNumber,
            @RequestBody MovementCreateRequest data
    ) {
        MovementDTO result = this.logic.createAccountMovement(accountNumber, movementsMapper.toDTO(data));
        return movementsMapper.toDetailResponse(result);
    }

    @GetMapping("{accountNumber}/movements")
    public List<MovementItemResponse> createAccount(
            @PathVariable String accountNumber
    ) {
        return this.logic.getAccountMovement(accountNumber)
                .stream()
                .map(this.movementsMapper::toItemResponse)
                .toList();
    }
}
