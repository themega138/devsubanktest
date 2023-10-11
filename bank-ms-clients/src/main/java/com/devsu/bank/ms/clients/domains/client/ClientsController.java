package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.client.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.client.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.commons.errors.ResourceNotFoundException;
import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("v1/clients")
public class ClientsController {

    private final IClientsLogic clientsLogic;

    public ClientsController(IClientsLogic clientsLogic) {
        this.clientsLogic = clientsLogic;
    }

    @GetMapping()
    public List<ClientItemResponse> paginateClients(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "20") final Integer size
    ) {
        return this.clientsLogic.paginateClients(PageRequest.of(pageNumber, size))
                .map(ClientsController::toClientResponse)
                .toList();
    }

    @GetMapping("{id}")
    public ClientDetailResponse getClientDetail(
            @RequestParam() final Long id
    ) {
        return this.clientsLogic.getClient(id)
                .map(ClientsController::toClientDetailResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping()
    public ClientDetailResponse createClient(
            @RequestBody() ClientCreateRequest data
    ) {
        ClientDTO result = this.clientsLogic.createClient(this.toClientDTO(data));
        return toClientDetailResponse(result);
    }

    private ClientDTO toClientDTO(ClientCreateRequest data) {
        return ClientDTO.builder()
                .address(data.address())
                .state(data.state())
                .phone(data.phone())
                .name(data.name())
                .personalId(data.personalId())
                .gender(Gender.valueOf(data.gender()))
                .build();
    }
    private static ClientDetailResponse toClientDetailResponse(ClientDTO dto) {
        return ClientDetailResponse.builder()
                .id(dto.id())
                .address(dto.address())
                .clientId(dto.clientId())
                .age(dto.age())
                .phone(dto.phone())
                .state(dto.state())
                .uuid(dto.uuid().toString())
                .name(dto.name())
                .gender(dto.gender().toString())
                .personalId(dto.personalId())
                .build();
    }
    private static ClientItemResponse toClientResponse(ClientDTO dto) {
        return ClientItemResponse.builder()
                .id(dto.id())
                .address(dto.address())
                .clientId(dto.clientId())
                .age(dto.age())
                .phone(dto.phone())
                .state(dto.state())
                .uuid(dto.uuid().toString())
                .name(dto.name())
                .gender(dto.gender().toString())
                .personalId(dto.personalId())
                .build();
    }

}
