package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.commons.ICrudLogic;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementDTO;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovementsLogic extends ICrudLogic<MovementDTO> {
    List<MovementDTO> paginate(Long clientId, LocalDateTime startDate, LocalDateTime endDate, PageRequest page);
}
