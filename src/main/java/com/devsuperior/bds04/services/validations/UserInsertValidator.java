package com.devsuperior.bds04.services.validations;

import com.devsuperior.bds04.dto.UserInsertDTO;
import com.devsuperior.bds04.repositories.UserRepository;
import com.devsuperior.bds04.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(hasUserWithThisEmail(dto.getEmail())) {
			list.add(new FieldMessage("email", "O email informado já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getfieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	private boolean hasUserWithThisEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}
