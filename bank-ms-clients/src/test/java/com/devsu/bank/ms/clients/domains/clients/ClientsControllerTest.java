package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.domains.clients.models.ClientCreateRequest;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDetailResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientItemResponse;
import com.devsu.bank.ms.clients.domains.clients.models.ClientUpdateRequest;
import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import com.github.javafaker.Faker;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientsControllerTest {

    private static Faker faker = new Faker();
    private static final Random random = new Random();

    @Mock
    private IClientsLogic clientsLogic;

    @Mock
    private ClientsMapperImpl clientsMapper = (ClientsMapperImpl) Mappers.getMapper(ClientsMapper.class);

    @InjectMocks
    private ClientsController controller;

    @Test
    void whenPaginateClient_shouldReturnAnEmptyList() {
        // GIVEN
        int pageNumber = 0;
        int size = 10;
        given(clientsLogic.paginate(any()))
                .willAnswer(invocationOnMock -> {
                    Pageable page = invocationOnMock.getArgument(0, Pageable.class);
                    assertThat(page.getPageNumber())
                            .isEqualTo(pageNumber);
                    assertThat(page.getPageSize())
                            .isEqualTo(size);
                    return new PageImpl<>(Collections.emptyList());
                });

        // WHEN
        List<ClientItemResponse> clientItemResponses = this.controller.paginateClients(pageNumber, size);

        // THEN
        assertThat(clientItemResponses)
                .isNotNull();

        assertThat(clientItemResponses)
                .isEmpty();
    }

    @Test
    void whenPaginateClient_shouldReturnList() {
        // GIVEN
        int pageNumber = 0;
        int size = 10;
        ClientDTO clientDTO = getFakeClientDTO();
        given(clientsLogic.paginate(any()))
                .willAnswer(invocationOnMock -> {
                    Pageable page = invocationOnMock.getArgument(0, Pageable.class);
                    assertThat(page.getPageNumber())
                            .isEqualTo(pageNumber);
                    assertThat(page.getPageSize())
                            .isEqualTo(size);
                    return new PageImpl<>(Lists.list(clientDTO));
                });

        when(clientsMapper.toItemResponse(any())).thenCallRealMethod();

        // WHEN
        List<ClientItemResponse> clientItemResponses = this.controller.paginateClients(pageNumber, size);

        // THEN
        assertThat(clientItemResponses)
                .isNotNull();

        assertThat(clientItemResponses)
                .isNotEmpty();

        ClientItemResponse item = clientItemResponses.stream().findFirst().get();

        assertThat(item)
                .isNotNull();

        assertThat(item)
                .usingRecursiveComparison()
                .ignoringFields("gender", "uid")
                .isEqualTo(clientDTO);

        assertThat(item.gender())
                .isEqualTo(clientDTO.gender().toString());

        assertThat(item.uid())
                .isEqualTo(clientDTO.uid().toString());
    }

    @Test
    void whenGetClientDetail_shouldReturnAnClientDetailResponse() {
        // GIVEN
        Long randomId = faker.random().nextLong();
        ClientDTO clientDTO = getFakeClientDTO();
        given(clientsLogic.getOne(randomId))
                .willReturn(clientDTO);

        when(clientsMapper.toDetailResponse(clientDTO)).thenCallRealMethod();

        // WHEN
        ClientDetailResponse detail = this.controller.getClientDetail(randomId);

        // THEN
        assertThat(detail)
                .isNotNull();

        assertThat(detail)
                .usingRecursiveComparison()
                .ignoringFields("gender", "uid")
                .isEqualTo(clientDTO);

        assertThat(detail.gender())
                .isEqualTo(clientDTO.gender().toString());

        assertThat(detail.uid())
                .isEqualTo(clientDTO.uid().toString());
    }

    @Test
    void whenCreateClient_shouldReturnAnNewClient() {
        // GIVEN
        ClientDTO clientDTO = getFakeClientDTO();
        ClientCreateRequest request = new ClientCreateRequest(
                faker.name().fullName(),
                getRandomGender().toString(),
                faker.number().numberBetween(1, 120),
                faker.idNumber().valid(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.internet().password(),
                faker.bool().bool());

        when(clientsMapper.toDTO(request)).thenCallRealMethod();

        given(clientsLogic.createOne(any())).willReturn(clientDTO);

        when(clientsMapper.toDetailResponse(clientDTO)).thenCallRealMethod();

        // WHEN
        ClientDetailResponse result = this.controller.createClient(request);

        // THEN
        assertThat(result)
                .isNotNull();

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("gender", "uid")
                .isEqualTo(clientDTO);

        assertThat(result.gender())
                .isEqualTo(clientDTO.gender().toString());

        assertThat(result.uid().toString());
    }

    @Test
    void whenDeleteClient_shouldTryDeleted() {
        // GIVEN
        Long randomId = faker.random().nextLong();
        doNothing().when(clientsLogic).deleteOne(randomId);

        // WHEN
        this.controller.deleteClient(randomId);

        // THEN
        verify(clientsLogic, times(1)).deleteOne(randomId);
    }

    @Test
    void whenUpdateClient_shouldTryUpdate() {
        // GIVEN
        Long randomId = faker.number().randomNumber();
        ClientUpdateRequest request = new ClientUpdateRequest(
                faker.name().fullName(),
                getRandomGender().toString(),
                faker.number().numberBetween(1, 120),
                faker.idNumber().valid(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.bool().bool());

        ClientDTO dto = clientsMapper.toDTO(request);
        given(clientsMapper.toDTO(request)).willReturn(dto);

        doNothing().when(clientsLogic).updateOne(any(), any());

        // WHEN
        this.controller.updateClient(randomId, request);

        // THEN
        verify(clientsLogic, times(1)).updateOne(randomId, dto);
    }

    private ClientDTO getFakeClientDTO() {
        return ClientDTO.builder()
                .id(faker.number().randomNumber())
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
