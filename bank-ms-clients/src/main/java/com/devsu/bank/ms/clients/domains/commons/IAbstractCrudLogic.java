package com.devsu.bank.ms.clients.domains.commons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAbstractCrudLogic<DTO> {

    Page<DTO> paginate(Pageable page);

    DTO getOne(Long id);

    DTO createOne(DTO dto);

    void deleteOne(Long id);

    void updateOne(Long id, DTO dto);
}
