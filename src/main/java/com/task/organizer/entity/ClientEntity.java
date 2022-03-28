package com.task.organizer.entity;

import com.task.organizer.dto.ClientDto;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "client")
public class ClientEntity {
	private Integer id;
	private String fullName;
	private String position;
	private String company;
	private String email;
	private List<String> phones;

	public static ClientDto toDto(ClientEntity entity) {
		ClientDto dto = new ClientDto();
		dto.setId(entity.id);
		dto.setFullName(entity.fullName);
		dto.setPosition(entity.position);
		dto.setCompany(entity.company);
		dto.setEmail(entity.email);
		dto.setPhones(entity.phones);
		return dto;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}
