package com.devsu.bank.ms.accounts.domains.accounts;

import com.devsu.bank.ms.accounts.domains.accounts.models.Account;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountCreateRequest;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDTO;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountDetailResponse;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountItemResponse;
import com.devsu.bank.ms.accounts.domains.accounts.models.AccountUpdateRequest;
import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import com.devsu.bank.ms.accounts.domains.commons.ICrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountsMapper extends ICrudMapper<AccountDTO, Account> {

    AccountItemResponse toItemResponse(AccountDTO accountDTO);

    AccountDetailResponse toDetailResponse(AccountDTO one);

    AccountDTO toDTO(AccountCreateRequest data);

    AccountDTO toDTO(AccountUpdateRequest data);

    AccountDTO updateDTO(@MappingTarget AccountDTO account, ClientDTO client);
}
