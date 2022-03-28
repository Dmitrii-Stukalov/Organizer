package com.task.organizer.service;

import com.task.organizer.dto.ClientDto;
import com.task.organizer.entity.ClientEntity;
import com.task.organizer.entity.ClientsWrapper;
import com.task.organizer.repository.ClientsRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.task.organizer.dto.ClientDto.toEntity;
import static com.task.organizer.entity.ClientEntity.toDto;

@Service
public class ClientsService {

	private final ClientsRepository clientsRepository;

	public ClientsService(ClientsRepository clientsRepository) {
		this.clientsRepository = clientsRepository;
	}

	public void addClient(ClientDto clientDto) {
		ClientEntity clientEntity = toEntity(clientDto);

		ClientsWrapper clientsWrapper = clientsRepository.getClientsFromXml();
		List<ClientEntity> clientsList = clientsWrapper.getClients();
		clientsList.add(clientEntity);
		clientsWrapper.setClients(clientsList);

		clientsRepository.setClientsToXml(clientsWrapper);
	}

	public void deleteClient(Integer clientId) {
		ClientsWrapper clientsWrapper = clientsRepository.getClientsFromXml();
		List<ClientEntity> clientsList = clientsWrapper.getClients();
		if (!clientsList.removeIf(client -> clientId.equals(client.getId()))) {
			throw new IllegalArgumentException("Client not found");
		}
		clientsWrapper.setClients(clientsList);
		clientsRepository.setClientsToXml(clientsWrapper);
	}

	public void updateClient(ClientDto clientDto, Integer clientId) {
		ClientEntity clientEntity = toEntity(clientDto);

		ClientsWrapper clientsWrapper = clientsRepository.getClientsFromXml();
		List<ClientEntity> clientsList = clientsWrapper.getClients();

		ClientEntity clientToUpdate = getClientEntity(clientsList, clientId);

		clientToUpdate.setFullName(ObjectUtils.firstNonNull(clientEntity.getFullName(), clientToUpdate.getFullName()));
		clientToUpdate.setCompany(ObjectUtils.firstNonNull(clientEntity.getCompany(), clientToUpdate.getCompany()));
		clientToUpdate.setPosition(ObjectUtils.firstNonNull(clientEntity.getPosition(), clientToUpdate.getPosition()));
		clientToUpdate.setEmail(ObjectUtils.firstNonNull(clientEntity.getEmail(), clientToUpdate.getEmail()));
		if (!CollectionUtils.isEmpty(clientEntity.getPhones())) {
			clientToUpdate.setPhones(clientEntity.getPhones());
		}

		clientsWrapper.setClients(clientsList);

		clientsRepository.setClientsToXml(clientsWrapper);
	}

	public ClientDto getClient(Integer clientId) {

		ClientsWrapper clientsWrapper = clientsRepository.getClientsFromXml();
		List<ClientEntity> clientsList = clientsWrapper.getClients();

		ClientEntity clientEntity = getClientEntity(clientsList, clientId);

		return toDto(clientEntity);
	}

	private ClientEntity getClientEntity(List<ClientEntity> clientsList, Integer clientId) {
		return clientsList.stream()
				.filter(client -> clientId.equals(client.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}

	public List<ClientDto> getAllClients() {
		return clientsRepository.getClientsFromXml()
				.getClients()
				.stream()
				.map(ClientEntity::toDto)
				.collect(Collectors.toList());
	}
}
