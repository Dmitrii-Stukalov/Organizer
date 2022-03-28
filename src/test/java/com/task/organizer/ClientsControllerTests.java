package com.task.organizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.organizer.controller.ClientsController;
import com.task.organizer.dto.ClientDto;
import com.task.organizer.service.ClientsService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ClientsController.class)
public class ClientsControllerTests {
	ObjectMapper objectMapper = new ObjectMapper();
	@MockBean
	private ClientsService clientsService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addClient() throws Exception {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		mockMvc.perform(post("/api/v1/clients")
						.content(objectMapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void addAndDeleteClient() throws Exception {

		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		mockMvc.perform(post("/api/v1/clients")
						.content(objectMapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


		mockMvc.perform(delete("/api/v1/clients/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void getClient() throws Exception {

		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		mockMvc.perform(post("/api/v1/clients")
						.content(objectMapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


		mockMvc.perform(get("/api/v1/clients/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void updateClient() throws Exception {

		ClientDto clientDto = new ClientDto();
		clientDto.setId(1);
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setEmail("user@user.user");
		clientDto.setCompany("Aboba");
		clientDto.setFullName("Aboba Aboba Aboba");
		clientDto.setPosition("Senior Aboba");

		mockMvc.perform(post("/api/v1/clients")
						.content(objectMapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


		ClientDto clientDto2 = new ClientDto();
		clientDto.setPhones(Lists.list("123-1223", "1111-1221233"));
		clientDto.setCompany("Doma");
		clientDto.setPosition("Junior Aboba");

		mockMvc.perform(put("/api/v1/clients/1")
						.content(objectMapper.writeValueAsString(clientDto2))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		;
	}
}
