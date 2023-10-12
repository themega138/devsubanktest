package com.devsu.bank.ms.clients.domains.client;

import com.devsu.bank.ms.clients.domains.client.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.client.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.client.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.client.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.client.models.ClientUpdateRequest;
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
@RequestMapping("v1/clients")
public class ClientsController {

    private final IClientsLogic clientsLogic;
    private final ClientsMapper mapper;

    public ClientsController(IClientsLogic clientsLogic, ClientsMapper mapper) {
        this.clientsLogic = clientsLogic;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<ClientItemResponse> paginateClients(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "20") final Integer size
    ) {
        return this.clientsLogic.paginate(PageRequest.of(pageNumber, size))
                .map(mapper::toItemResponse)
                .toList();
    }

    @GetMapping("{id}")
    public ClientDetailResponse getClientDetail(
            @PathVariable Long id) {
        return mapper.toDetailResponse(this.clientsLogic.getOne(id));
    }

    @PostMapping()
    public ClientDetailResponse createClient(
            @RequestBody() ClientCreateRequest data
    ) {
        ClientDTO result = this.clientsLogic.createOne(mapper.toDTO(data));
        return mapper.toDetailResponse(result);
    }

    @DeleteMapping("{id}")
    public void deleteClient(
            @PathVariable Long id) {
        this.clientsLogic.deleteOne(id);
    }

    @PutMapping("{id}")
    public void updateClient(
            @PathVariable Long id,
            @RequestBody ClientUpdateRequest data
            ) {
        this.clientsLogic.updateOne(id, mapper.toDTO(data));
    }

}
