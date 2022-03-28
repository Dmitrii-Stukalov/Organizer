package com.task.organizer.entity;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "clients")
public class ClientsWrapper {
	private List<ClientEntity> clients;

	public List<ClientEntity> getClients() {
		return clients;
	}

	@XmlElement(name = "client")
	public void setClients(List<ClientEntity> clients) {
		this.clients = clients;
	}
}
