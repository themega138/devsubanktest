package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.domains.clients.models.Client;
import com.devsu.bank.ms.clients.domains.clients.models.ClientDTO;
import com.devsu.bank.ms.clients.domains.commons.errors.ResourceNotFoundException;
import com.devsu.bank.ms.clients.domains.commons.models.Gender;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultClientsLogicImplTest {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final ClientsMapper mapper = Mappers.getMapper(ClientsMapper.class);

    @Mock
    private ClientsRepository clientsRepository;

    @Mock
    private ClientsMapper clientsMapper;

    @InjectMocks
    private DefaultClientsLogicImpl clientsLogic;

    @Test
    void whenPaginate_ShouldReturnAnEmptyPage() {
        // GIVEN
        Pageable pageable = Pageable.ofSize(10);
        given(clientsRepository.findAll(pageable))
                .willReturn(Page.empty());

        // WHEN
        Page<ClientDTO> page = clientsLogic.paginate(pageable);

        // THEN
        assertThat(page)
                .isNotNull();

        List<ClientDTO> list = page.toList();

        assertThat(list)
                .isEmpty();
    }

    @Test
    void whenPaginate_shouldReturnAPageWithOneElement() {
        // GIVEN
        Pageable pageable = Pageable.ofSize(10);
        Client client = getFakeClient();
        given(clientsRepository.findAll(pageable))
                .willReturn(new PageImpl<>(Collections.singletonList(client)));

        given(clientsMapper.toDTO(client))
                .willReturn(mapper.toDTO(client));

        // WHEN
        Page<ClientDTO> page = clientsLogic.paginate(pageable);

        // THEN
        assertThat(page)
                .isNotNull();

        List<ClientDTO> list = page.toList();

        assertThat(list)
                .isNotEmpty();
        assertThat(list.size())
                .isOne();

        ClientDTO result = list.stream().findFirst().get();
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    void whenGetOne_shouldThrowAnNotFoundException() {
        // GIVEN
        Long randomId = faker.random().nextLong();
        given(clientsRepository.findById(randomId))
                .willReturn(Optional.empty());

        // WHEN
        Executable executable = () -> this.clientsLogic.getOne(randomId);

        //THEN
        assertThrows(ResourceNotFoundException.class, executable);
    }

    @Test
    void whenGetOne_shouldReturnOneElement() {
        // GIVEN
        Client client = getFakeClient();
        given(clientsRepository.findById(client.getId()))
                .willReturn(Optional.of(client));

        given(clientsMapper.toDTO(client))
                .willReturn(mapper.toDTO(client));

        // WHEN
        ClientDTO result = this.clientsLogic.getOne(client.getId());

        // THEN
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(client);
    }

    @Test
    void whenDeleteOne_shouldTryDelete() {
        // GIVEN
        Client client = getFakeClient();
        given(clientsRepository.findById(client.getId()))
                .willReturn(Optional.of(client));

        doNothing().when(clientsRepository).delete(client);

        // WHEN
        this.clientsLogic.deleteOne(client.getId());

        // THEN
        verify(clientsRepository, times(1)).delete(client);
    }

    @Test
    void whenDeleteOne_shouldThrowAException() {
        // GIVEN
        Long randomId = faker.number().randomNumber();
        given(clientsRepository.findById(randomId))
                .willReturn(Optional.empty());

        // WHEN
        Executable executable = () -> this.clientsLogic.deleteOne(randomId);;

        //THEN
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, executable);

        assertThat(ex.getMessage())
                .isEqualTo("The client with the id %s was not found...".formatted(randomId));
    }

    @Test
    void whenUpdateOne_shouldThrowAnException() {
        // GIVEN
        ClientDTO clientDTO = mapper.toDTO(getFakeClient());
        Long randomId = faker.number().randomNumber();
        given(clientsRepository.findById(randomId))
                .willReturn(Optional.empty());

        // WHEN
        Executable executable = () -> this.clientsLogic.updateOne(randomId, clientDTO);

        //THEN
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, executable);

        assertThat(ex.getMessage())
                .isEqualTo("The client with the id %s was not found...".formatted(randomId));
    }

    @Test
    void whenUpdateOne_shouldTryUpdate() {
        // GIVEN
        Client client = getFakeClient();
        ClientDTO clientDTO = mapper.toDTO(getFakeClient());
        given(clientsRepository.findById(client.getId()))
                .willReturn(Optional.of(client));

        Client updatedEntity = mapper.updateEntity(client, clientDTO);

        given(clientsMapper.updateEntity(client, clientDTO))
                .willReturn(updatedEntity);

        when(clientsRepository.save(updatedEntity)).thenReturn(updatedEntity);

        // WHEN
        this.clientsLogic.updateOne(client.getId(), clientDTO);

        // THEN
        verify(clientsRepository, times(1)).save(updatedEntity);
    }

    @Test
    void whenCreateOne_shouldCreateOne() {
        // GIVEN
        Client client = getFakeClient();
        ClientDTO clientDTO = mapper.toDTO(client);

        given(clientsMapper.toEntity(any()))
                .willReturn(client);

        given(clientsRepository.save(any()))
                .willReturn(client);

        given(clientsMapper.updateDTOPassword(any(), anyString()))
                .willReturn(clientDTO);

        given(clientsMapper.toDTO(client))
                .willReturn(clientDTO);

        // WHEN
        ClientDTO result = this.clientsLogic.createOne(clientDTO);

        // THEN
        assertThat(result)
                .isNotNull();

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(client);

    }

    private Client getFakeClient() {
        return Client.builder()
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
