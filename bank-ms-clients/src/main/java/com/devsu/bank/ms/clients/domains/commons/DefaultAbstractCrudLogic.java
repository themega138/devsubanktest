package com.devsu.bank.ms.clients.domains.commons;

import com.devsu.bank.ms.clients.domains.commons.errors.ResourceNotFoundException;
import com.devsu.bank.ms.clients.domains.commons.models.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Supplier;

public abstract class DefaultAbstractCrudLogic<DTO, ENTITY extends AbstractEntity> implements IAbstractCrudLogic<DTO> {

    private final AbstractEntityRepo<ENTITY> repository;
    private final ICrudMapper<DTO, ENTITY> mapper;

    protected DefaultAbstractCrudLogic(AbstractEntityRepo<ENTITY> repository, ICrudMapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<DTO> paginate(Pageable page) {
        return this.repository.findAll(page)
                .map(mapper::toDTO);
    }

    @Override
    public DTO getOne(Long id) {
        return this.repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public DTO createOne(DTO dto) {
        ENTITY result = this.repository.save(mapper.toEntity(dto));
        return mapper.toDTO(result);
    }

    @Override
    public void deleteOne(Long id) {
        ENTITY entity = getOptionalEntity(id);
        this.repository.delete(entity);
    }

    @Override
    public void updateOne(Long id, DTO dto) {
        ENTITY entity = getOptionalEntity(id);
        this.repository.save(mapper.updateEntity(entity, dto));
    }

    private ENTITY getOptionalEntity(Long id) {
        Supplier<ResourceNotFoundException> exceptionSupplier =
                () -> new ResourceNotFoundException("The client with the id %s was not found...".formatted(id));
        return this.repository.findById(id)
                .orElseThrow(exceptionSupplier);
    }
}
