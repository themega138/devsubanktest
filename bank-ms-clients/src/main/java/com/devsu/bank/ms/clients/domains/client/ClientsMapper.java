package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.Client;
import com.devsu.bank.ms.clients.domains.client.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.client.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.client.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.client.models.ClientUpdateRequest;
import com.devsu.bank.ms.clients.domains.commons.ICrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class}
)
public interface ClientsMapper extends ICrudMapper<ClientDTO, Client> {

    ClientDTO toDTO(ClientCreateRequest client);

    ClientDTO toDTO(ClientUpdateRequest client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "clientId", ignore = true)
    @Mapping(target = "password", ignore = true)
    Client updateEntity(@MappingTarget Client entity, ClientDTO data);

    ClientItemResponse toItemResponse(ClientDTO dto);

    ClientDetailResponse toDetailResponse(ClientDTO dto);

}
