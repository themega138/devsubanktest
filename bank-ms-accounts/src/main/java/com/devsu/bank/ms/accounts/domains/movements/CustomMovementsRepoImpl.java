package com.devsu.bank.ms.accounts.domains.movements;

import com.devsu.bank.ms.accounts.domains.accounts.models.Account;
import com.devsu.bank.ms.accounts.domains.commons.errors.ResourceNotFoundException;
import com.devsu.bank.ms.accounts.domains.commons.errors.WrongMovementException;
import com.devsu.bank.ms.accounts.domains.movements.models.Movement;
import com.devsu.bank.ms.accounts.domains.movements.models.MovementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;

public class CustomMovementsRepoImpl implements CustomMovementsRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movement createMovement(Movement movement) {
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> q = cb.createQuery(Account.class);
        Root<Account> c = q.from(Account.class);
        q.select(c);
        q.where(cb.or(
                cb.equal(c.get("number"), movement.getAccount().getNumber()),
                cb.equal(c.get("uid"), movement.getAccount().getUid()),
                cb.equal(c.get("id"), movement.getAccount().getId())
        ));

        Account account = entityManager.createQuery(q)
                .getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Account with number %s was not found...".formatted(movement.getAccount().getNumber())));

        if (MovementType.WITHDRAW == movement.getType() && account.getBalance().doubleValue() < Math.abs(movement.getAmount().doubleValue())) {
            throw new WrongMovementException("The account does not have enough balance to do this movement...");
        }

        BigDecimal newBalance = account.getBalance().add(movement.getAmount());
        account.setBalance(newBalance);
        movement.setAccount(account);
        movement.setCurrentBalance(newBalance);

        entityManager.persist(movement);
        entityManager.persist(account);

        entityManager.getTransaction().commit();

        return movement;
    }
}
