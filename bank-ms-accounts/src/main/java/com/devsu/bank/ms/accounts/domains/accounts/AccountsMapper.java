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
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountsMapper extends ICrudMapper<AccountDTO, Account> {

    AccountItemResponse toItemResponse(AccountDTO accountDTO);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientUid", source = "client.uid")
    AccountDetailResponse toDetailResponse(AccountDTO one);

    @Override
    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.uid", source = "clientUid")
    AccountDTO toDTO(Account account);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", defaultExpression = "java(UUID.randomUUID())")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.uid", target = "clientUid")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.uid", source = "clientUid")
    AccountDTO toDTO(AccountCreateRequest data);

    AccountDTO toDTO(AccountUpdateRequest data);

    AccountDTO updateDTO(@MappingTarget AccountDTO account, ClientDTO client);
}
