package com.task.organizer;

import com.task.organizer.dto.ClientDto;
import com.task.organizer.entity.ClientEntity;
import com.task.organizer.entity.ClientsWrapper;
import com.task.organizer.repository.ClientsRepository;
import com.task.organizer.service.ClientsService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ClientsServiceTests {
	@InjectMocks
	ClientsService clientsService;

	@Mock
	ClientsRepository clientsRepository;

	@Before
	public void init() {
		ClientsWrapper wrapper = new ClientsWrapper();
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setId(0);
		clientEntity.setFullName("test");
		clientEntity.setCompany("Test");
		clientEntity.setEmail("test@test.test");
		clientEntity.setPosition("Middle Aboba");
		clientEntity.setPhones(Lists.list("123-123-123", "11-11-11"));
		wrapper.setClients(Lists.list(clientEntity));
		when(clientsRepository.getClientsFromXml()).thenReturn(wrapper);
	}

	@Test
	public void addClient() {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");
		clientsService.addClient(clientDto);

		ClientDto client = clientsService.getClient(1);
		assertThat(client.getEmail()).isSameAs(clientDto.getEmail());
		assertThat(client.getFullName()).isSameAs(clientDto.getFullName());
		assertThat(client.getId()).isSameAs(clientDto.getId());
		assertThat(client.getCompany()).isSameAs(clientDto.getCompany());
		assertThat(client.getPosition()).isSameAs(clientDto.getPosition());
	}

	@Test
	public void deleteClient() {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		clientsService.addClient(clientDto);
		ClientDto client = clientsService.getClient(1);
		assertThat(client.getId()).isEqualTo(1);

		clientsService.deleteClient(1);

		IllegalArgumentException illegalArgumentException = catchThrowableOfType(() -> clientsService.getClient(1),
				IllegalArgumentException.class);
		assertThat(illegalArgumentException.getMessage()).isSameAs("Client not found");
	}

	@Test
	public void updateClient() {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		clientsService.addClient(clientDto);

		ClientDto clientUpdateDto = new ClientDto();
		clientUpdateDto.setEmail("ds@ds.ds");
		clientUpdateDto.setCompany("Test");
		clientUpdateDto.setPosition("Junior Aboba");

		clientsService.updateClient(clientUpdateDto, 1);

		ClientDto client = clientsService.getClient(1);
		assertThat(client.getEmail()).isSameAs(clientUpdateDto.getEmail());
		assertThat(client.getCompany()).isSameAs(clientUpdateDto.getCompany());
		assertThat(client.getPosition()).isSameAs(clientUpdateDto.getPosition());
	}

	@Test
	public void getClient() {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		clientsService.addClient(clientDto);

		ClientDto client = clientsService.getClient(1);

		assertThat(client.getEmail()).isSameAs(clientDto.getEmail());
		assertThat(client.getFullName()).isSameAs(clientDto.getFullName());
		assertThat(client.getId()).isSameAs(clientDto.getId());
		assertThat(client.getCompany()).isSameAs(clientDto.getCompany());
		assertThat(client.getPosition()).isSameAs(clientDto.getPosition());
	}

	@Test
	public void getAllClients() {
		ClientDto clientDto1 = new ClientDto();
		clientDto1.setId(1);
		clientDto1.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto1.setEmail("user@user.user");
		clientDto1.setCompany("Aboba");
		clientDto1.setFullName("Aboba Aboba Aboba");
		clientDto1.setPosition("Senior Aboba");

		ClientDto clientDto2 = new ClientDto();
		clientDto2.setId(2);
		clientDto2.setPhones(Lists.list("22233-1223", "223-1221233-13-12"));
		clientDto2.setEmail("test@test.test");
		clientDto2.setCompany("Test company");
		clientDto2.setFullName("Dima Dima Dima");
		clientDto2.setPosition("Junior Aboba");

		clientsService.addClient(clientDto1);
		clientsService.addClient(clientDto2);

		List<ClientDto> clients = clientsService.getAllClients();

		assertThat(clients.size()).isEqualTo(3);
	}
}
