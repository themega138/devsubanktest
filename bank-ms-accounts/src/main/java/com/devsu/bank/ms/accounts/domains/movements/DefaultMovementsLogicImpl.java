package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.commons.DefaultAbstractCrudLogic;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultMovementsLogicImpl extends DefaultAbstractCrudLogic<MovementDTO, Movement> implements IMovementsLogic {

    private final MovementsRepo movementsRepo;
    private final MovementsMapper mapper;

    public DefaultMovementsLogicImpl(MovementsRepo movementsRepo, MovementsMapper mapper) {
        super(movementsRepo, mapper);
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
}
