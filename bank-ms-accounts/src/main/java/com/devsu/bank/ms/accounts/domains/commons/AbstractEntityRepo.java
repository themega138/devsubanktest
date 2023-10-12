package com.devsu.bank.ms.accounts.domains.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractEntityRepo<ENTITY> extends JpaRepository<ENTITY, Long> {
}
