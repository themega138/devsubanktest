package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.domains.clients.models.Client;
import com.devsu.bank.ms.clients.domains.clients.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientUpdateRequest;
import com.devsu.bank.ms.clients.domains.commons.ICrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class}
)
public interface ClientsMapper extends ICrudMapper<ClientDTO, Client> {

    ClientDTO toDTO(ClientCreateRequest client);

    ClientDTO toDTO(ClientUpdateRequest client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "password", ignore = true)
    Client updateEntity(@MappingTarget Client entity, ClientDTO data);

    ClientItemResponse toItemResponse(ClientDTO dto);

    ClientDetailResponse toDetailResponse(ClientDTO dto);

    @Mapping(target = "password", source = "password")
    ClientDTO updateDTOPassword(ClientDTO dto, String password);
}
