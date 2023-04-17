package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.services.validations.UserInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@UserInsertValid
public class UserInsertDTO extends UserDTO implements Serializable {
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 5, message = "A senha deve conter ao menos {min} caracteres")
	private String password;
	public UserInsertDTO() {
		super();
	}
		
	public UserInsertDTO(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}










