package com.task.organizer.controller;

import com.task.organizer.service.ClientsService;
import com.task.organizer.dto.ClientDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/clients")
public class ClientsController {
	private final ClientsService clientsService;

	public ClientsController(ClientsService clientsService) {
		this.clientsService = clientsService;
	}

	@PostMapping
	public void addClient(@RequestBody ClientDto client) {
		clientsService.addClient(client);
	}

	@DeleteMapping("/{clientId}")
	public void deleteClient(@PathVariable("clientId") Integer clientId) {
		clientsService.deleteClient(clientId);
	}

	@PutMapping(path = "/{clientId}")
	public void editClient(@RequestBody ClientDto client, @PathVariable("clientId") Integer clientId) {
		clientsService.updateClient(client, clientId);
	}

	@GetMapping
	public List<ClientDto> getAllClients() {
		return clientsService.getAllClients();
	}

	@GetMapping(path = "/{clientId}")
	public ClientDto getClient(@PathVariable("clientId") Integer clientId) {
		return clientsService.getClient(clientId);
	}
}
