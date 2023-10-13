package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.domains.clients.models.Client;
import com.devsu.bank.ms.clients.domains.clients.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientUpdateRequest;
import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.UUID;

public class ClientsMapperTest {
    private static Faker faker = new Faker();
    private static final Random random = new Random();
    private static final ClientsMapper mapper = Mappers.getMapper(ClientsMapper.class);
    @Test
    void when_fromClientCreateRequest_toDTO() {
        // GIVEN
        ClientCreateRequest request = new ClientCreateRequest(
                faker.name().fullName(),
                getRandomGender().toString(),
                faker.number().numberBetween(1, 120),
                faker.idNumber().valid(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.internet().password(),
                faker.bool().bool());

        // WHEN
        ClientDTO dto = mapper.toDTO(request);

        // THEN
        assertThat(dto)
                .isNotNull();

        assertThat(dto)
                .usingRecursiveComparison()
                .ignoringFields("id", "uid", "gender")
                .isEqualTo(request);

        assertThat(dto.gender())
                .isEqualTo(Gender.valueOf(request.gender()));
    }

    @Test
    void when_fromClientUpdateRequest_toDTO() {
        // GIVEN
        ClientUpdateRequest request = new ClientUpdateRequest(
                faker.name().fullName(),
                getRandomGender().toString(),
                faker.number().numberBetween(1, 120),
                faker.idNumber().valid(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.bool().bool());

        // WHEN
        ClientDTO dto = mapper.toDTO(request);

        // THEN
        assertThat(dto)
                .isNotNull();

        assertThat(dto)
                .usingRecursiveComparison()
                .ignoringFields("id", "uid", "password", "gender")
                .isEqualTo(request);

        assertThat(dto.gender())
                .isEqualTo(Gender.valueOf(request.gender()));
    }

    @Test
    void when_fromClientAndClientDTO_updateEntity() {
        // GIVEN
        Client client = getFakeClient();
        ClientDTO clientDTO = getFakeClientDTO();

        // WHEN
        Client updateEntity = mapper.updateEntity(client, clientDTO);

        // THEN
        assertThat(updateEntity)
                .isNotNull();

        assertThat(updateEntity)
                .usingRecursiveComparison()
                .ignoringFields("id", "uid", "password")
                .isEqualTo(clientDTO);

        assertThat(updateEntity.getId())
                .isEqualTo(client.getId());

        assertThat(updateEntity.getPassword())
                .isEqualTo(client.getPassword());

        assertThat(updateEntity.getUid())
                .isEqualTo(client.getUid());
    }

    @Test
    void when_fromDTO_toClientItemResponse() {
        // GIVEN
        ClientDTO clientDTO = getFakeClientDTO();

        // WHEN
        ClientItemResponse itemResponse = mapper.toItemResponse(clientDTO);

        // THEN
        assertThat(itemResponse)
                .isNotNull();

        assertThat(itemResponse)
                .usingRecursiveComparison()
                .ignoringFields("gender", "uid")
                .isEqualTo(clientDTO);

        assertThat(itemResponse.gender())
                .isEqualTo(clientDTO.gender().toString());

        assertThat(itemResponse.uid())
                .isEqualTo(clientDTO.uid().toString());
    }

    @Test
    void when_fromDTO_toDetailResponse() {
        // GIVEN
        ClientDTO clientDTO = getFakeClientDTO();

        // WHEN
        ClientDetailResponse detailResponse = mapper.toDetailResponse(clientDTO);

        // THEN
        assertThat(detailResponse)
                .usingRecursiveComparison()
                .ignoringFields("gender", "uid")
                .isEqualTo(clientDTO);

        assertThat(detailResponse.gender())
                .isEqualTo(clientDTO.gender().toString());

        assertThat(detailResponse.uid())
                .isEqualTo(clientDTO.uid().toString());

    }
    @Test
    void when_fromClientDTOAndPassword_updateDTOPassword() {
        // GIVEN
        ClientDTO clientDTO = getFakeClientDTO();
        String newPassword = faker.internet().password();

        // WHEN
        ClientDTO updatedClient = mapper.updateDTOPassword(clientDTO, newPassword);

        // THEN
        assertThat(updatedClient)
                .isNotNull();

        assertThat(updatedClient)
                .usingRecursiveComparison()
                .ignoringFields("password")
                .isEqualTo(clientDTO);

        assertThat(updatedClient.password())
                .isEqualTo(newPassword);
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

    private ClientDTO getFakeClientDTO() {
        return ClientDTO.builder()
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
