package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.domains.clients.models.Client;
import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClientsRepositoryTest {

    private final Faker faker = new Faker();
    private static final Random random = new Random();

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientsRepository clientsRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(entityManager);
        assertNotNull(clientsRepository);
    }

    @Test
    void whenSave_shouldSaveItAndFindIt() {
        // GIVEN
        Client client = getFakeClient();

        // WHEN
        Client savedClient = this.clientsRepository.save(client);

        // THEN
        Long id = savedClient.getId();
        Client retreivedClient = this.entityManager.find(Client.class, id);

        assertThat(retreivedClient)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(client);
    }

    @Test
    void whenFindById_shouldGetIt() {
        // GIVEN
        Client client = getFakeClient();
        entityManager.persist(client);
        entityManager.flush();

        // WHEN
        Optional<Client> byId = this.clientsRepository.findById(client.getId());

        // THEN
        assertThat(byId.isPresent())
                .isTrue();
        Client result = byId.get();
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    void whenFindAllPaged_shouldReturnAPageWithOneElement() {
        // GIVEN
        Client client = getFakeClient();
        entityManager.persist(client);
        entityManager.flush();

        Pageable pageable = Pageable.ofSize(10);

        // WHEN
        Page<Client> page = this.clientsRepository.findAll(pageable);

        // THEN
        assertThat(page.getTotalElements())
                .isEqualTo(1);
        List<Client> clientsList = page.toList();
        Optional<Client> first = clientsList.stream().findFirst();
        assertThat(first.isPresent())
                .isTrue();
        Client retreivedClient = first.get();
        assertThat(retreivedClient)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    void whenDeleteOne_shouldRemoveIt() {
        // GIVEN
        Client client = getFakeClient();
        entityManager.persist(client);
        entityManager.flush();

        // WHEN
        this.clientsRepository.delete(client);

        // THEN
        Client deletedClient = entityManager.find(Client.class, client.getId());
        assertThat(deletedClient)
                .isNull();
    }

    @Test
    void whenUpdateOne_shouldUpdateIt() {
        // GIVEN
        Client client = getFakeClient();
        entityManager.persist(client);
        entityManager.flush();

        Client updatedClient = getFakeClient();
        updatedClient.setId(client.getId());

        // WHEN
        Client saved = this.clientsRepository.save(updatedClient);

        // THEN
        assertThat(saved)
                .isNotNull();

        assertThat(saved.getId())
                .isEqualTo(client.getId());

        assertThat(saved)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(updatedClient);

    }

    private Client getFakeClient() {
        return Client.builder()
                .password(faker.internet().password())
                .state(faker.bool().bool())
                .age(faker.number().numberBetween(1, 120))
                .address(faker.address().fullAddress())
                .gender(getRandomGender())
                .name(faker.name().fullName())
                .personalId(faker.idNumber().valid())
                .phone(faker.phoneNumber().cellPhone())
                .uid(UUID.randomUUID())
                .build();
    }

    private Gender getRandomGender() {
        Gender[] genders = Gender.values();
        return genders[random.nextInt(genders.length)];
    }

}
