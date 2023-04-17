package com.devsuperior.bds04.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;

import javax.validation.constraints.NotBlank;

public class CityDTO implements Serializable {
	private Long id;

	@NotBlank(message = "Campo requerido")
	private String name;

	public CityDTO() {
	}

	public CityDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CityDTO(City entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
