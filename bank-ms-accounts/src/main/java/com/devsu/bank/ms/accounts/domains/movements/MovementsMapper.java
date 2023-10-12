package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.commons.ICrudMapper;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementCreateRequest;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDetailResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementItemResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class, LocalDateTime.class}
)
public interface MovementsMapper extends ICrudMapper<MovementDTO, Movement> {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", defaultExpression = "java(UUID.randomUUID())")
    @Mapping(target = "date", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "state", defaultValue = "true")
    Movement toEntity(MovementDTO movementDTO);

    MovementItemResponse toItemResponse(MovementDTO movementDTO);

    MovementDetailResponse toDetailResponse(MovementDTO one);

    MovementDTO toDTO(MovementCreateRequest data);

    MovementDTO toDTO(MovementUpdateRequest data);
}
