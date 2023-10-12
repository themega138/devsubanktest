package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.movements.models.MovementCreateRequest;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDetailResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementItemResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementReportItemResponse;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementUpdateRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping("v1/movements")
public class MovementsController {

    private final IMovementsLogic logic;
    private final MovementsMapper mapper;

    public MovementsController(IMovementsLogic logic, MovementsMapper mapper) {
        this.logic = logic;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<MovementItemResponse> paginateMovements(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "20") final Integer size
    ) {
        return this.logic.paginate(PageRequest.of(pageNumber, size))
                .map(mapper::toItemResponse)
                .toList();
    }

    @GetMapping("{clientId}/report")
    public List<MovementReportItemResponse> reportClientMovements(
            @PathVariable final Long clientId,
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            final LocalDateTime startDate,
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            final LocalDateTime endDate,
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "100") final Integer size
    ) {
        return this.logic.paginate(clientId, startDate, endDate, PageRequest.of(pageNumber, size))
                .stream()
                .map(mapper::toReportItemResponse)
                .toList();
    }

    @GetMapping("{id}")
    public MovementDetailResponse getClientDetail(
            @PathVariable Long id) {
        return mapper.toDetailResponse(this.logic.getOne(id));
    }

    @PostMapping()
    public MovementDetailResponse createClient(
            @RequestBody() MovementCreateRequest data
    ) {
        MovementDTO result = this.logic.createOne(mapper.toDTO(data));
        return mapper.toDetailResponse(result);
    }

    @DeleteMapping("{id}")
    public void deleteClient(
            @PathVariable Long id) {
        this.logic.deleteOne(id);
    }

    @PutMapping("{id}")
    public void updateClient(
            @PathVariable Long id,
            @RequestBody MovementUpdateRequest data
    ) {
        this.logic.updateOne(id, mapper.toDTO(data));
    }
}
