package com.task.organizer.dto;

import com.task.organizer.entity.ClientEntity;

import java.util.List;

public class ClientDto {
	private Integer id;
	private String fullName;
	private String position;
	private String company;
	private String email;
	private List<String> phones;

	public static ClientEntity toEntity(ClientDto dto) {
		ClientEntity entity = new ClientEntity();
		entity.setId(dto.id);
		entity.setFullName(dto.fullName);
		entity.setPosition(dto.position);
		entity.setCompany(dto.company);
		entity.setEmail(dto.email);
		entity.setPhones(dto.phones);
		return entity;
	}

	public Integer getId() {
		return id;
	}

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
