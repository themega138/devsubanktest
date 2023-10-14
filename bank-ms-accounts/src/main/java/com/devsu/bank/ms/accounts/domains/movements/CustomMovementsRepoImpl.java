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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomMovementsRepoImpl implements CustomMovementsRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Movement createMovement(Movement movement) {
        if (movement.getAmount().doubleValue() == 0.0) {
            throw new WrongMovementException("The movement amount should not be 0.0");
        }
        if(movement.getAmount().doubleValue() > 0 && MovementType.WITHDRAW.equals(movement.getType())) {
            throw new WrongMovementException("Wrong movement type. Change the movement type to DEPOSIT or make the amount negative.");
        }
        if(movement.getAmount().doubleValue() < 0 && MovementType.DEPOSIT.equals(movement.getType())) {
            throw new WrongMovementException("Wrong movement type. Change the movement type to WITHDRAW or make the amount positive.");
        }

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

        movement.setAccount(account);

        movement.setInitialBalance(BigDecimal.valueOf(account.getBalance().doubleValue()));
        BigDecimal newBalance = BigDecimal.valueOf(account.getBalance().add(movement.getAmount()).doubleValue());
        account.setBalance(newBalance);
        movement.setAccount(account);
        movement.setCurrentBalance(newBalance);

        entityManager.persist(movement);
        entityManager.persist(account);


        return movement;
    }
}
