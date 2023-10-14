package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import com.devsu.bank.ms.accounts.domains.commons.ICrudMapper;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementCreateRequest;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDetailResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementItemResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementReportItemResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class, LocalDateTime.class}
)
public interface MovementsMapper extends ICrudMapper<MovementDTO, Movement> {

    @Override
    @Mapping(target = "account", source = "account")
    MovementDTO toDTO(Movement movement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", defaultExpression = "java(UUID.randomUUID())")
    @Mapping(target = "date", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "state", defaultValue = "true")
    Movement toEntity(MovementDTO movementDTO);

    @Mapping(target = "accountUid", source = "account.uid")
    @Mapping(target = "accountNumber", source = "account.number")
    MovementItemResponse toItemResponse(MovementDTO movementDTO);

    @Mapping(target = "account", source = "account")
    MovementDetailResponse toDetailResponse(MovementDTO one);

    MovementDTO toDTO(MovementCreateRequest data);

    MovementDTO toDTO(MovementUpdateRequest data);

    @Mapping(source = "client", target = "account.client")
    @Mapping(target = "uid", ignore = true)
    MovementDTO updateClient(@MappingTarget MovementDTO movement, ClientDTO client);

    @Mapping(target = "accountNumber", source = "account.number")
    @Mapping(target = "accountType", source = "account.type")
    @Mapping(target = "movementUid", source = "uid")
    @Mapping(target = "movementAmount", source = "amount")
    @Mapping(target = "movementType", source = "type")
    MovementReportItemResponse toReportItemResponse(MovementDTO movementDTO);

    MovementDTO toDTO(com.devsu.bank.ms.accounts.domains.accounts.models.MovementCreateRequest data);
}
