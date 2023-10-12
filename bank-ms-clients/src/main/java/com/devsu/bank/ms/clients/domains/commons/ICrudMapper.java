package com.devsu.bank.ms.clients.domains.commons;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface ICrudMapper<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", defaultExpression = "java( UUID.randomUUID())")
    ENTITY toEntity(DTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", ignore = true)
    ENTITY updateEntity(@MappingTarget ENTITY entity, DTO dto);

}
