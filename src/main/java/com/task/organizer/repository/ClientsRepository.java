package com.task.organizer.repository;

import com.task.organizer.entity.ClientsWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class ClientsRepository {
	private static final File FILE = new File("src/main/resources/clients.xml");

	public ClientsWrapper getClientsFromXml() {
		try {
			JAXBContext context = JAXBContext.newInstance(ClientsWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (ClientsWrapper) unmarshaller.unmarshal(FILE);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public void setClientsToXml(ClientsWrapper clientsWrapper) {
		try {
			JAXBContext context = JAXBContext.newInstance(ClientsWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(clientsWrapper, FILE);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
}
