package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.accounts.models.ClientDTO;
import com.devsu.bank.ms.accounts.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import com.devsu.bank.ms.accounts.services.clients.IClientsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultMovementsLogicImpl extends DefaultAbstractCrudLogic<MovementDTO, Movement> implements IMovementsLogic {

    private final IClientsService clientsService;
    private final MovementsRepo movementsRepo;
    private final MovementsMapper mapper;

    public DefaultMovementsLogicImpl(IClientsService clientsService, MovementsRepo movementsRepo, MovementsMapper mapper) {
        super(movementsRepo, mapper);
        this.clientsService = clientsService;
        this.movementsRepo = movementsRepo;
        this.mapper = mapper;
    }

    @Override
    public MovementDTO createOne(MovementDTO movementDTO) {
        Movement entity = this.mapper.toEntity(movementDTO);

        return Optional.of(this.movementsRepo.createMovement(entity))
                .map(this.mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Some error happened creating the movement..."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDTO> paginate(Long clientId, LocalDateTime startDate, LocalDateTime endDate, PageRequest page) {
        ClientDTO client = this.clientsService.getByClientId(clientId);
        return this.movementsRepo.findAllByAccount_ClientIdAndDateIsBetween(clientId, startDate, endDate, page)
                .map(this.mapper::toDTO)
                .map(movementDTO -> this.mapper.updateClient(movementDTO, client))
                .toList();
    }
}
