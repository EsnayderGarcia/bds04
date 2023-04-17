package com.devsuperior.bds04.resources.exceptions;

import com.devsuperior.bds04.services.exceptions.DataBaseException;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, 
			HttpServletRequest req){
			
		StandardError error = new StandardError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError(ex.getMessage());
		error.setPath(req.getRequestURI());
		
		return new ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataIntegretyViolation(DataBaseException ex,
			HttpServletRequest req) {
		
		StandardError error = new StandardError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError(ex.getMessage());
		error.setPath(req.getRequestURI());
		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> notValidArgument(MethodArgumentNotValidException ex,
			HttpServletRequest req) {
		ValidationError error = new ValidationError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setError("Um ou mais campos estão inválidos");
		error.setPath(req.getRequestURI());
		
		ex.getBindingResult()
			.getFieldErrors()
			.forEach(f -> error.addError(new FieldMessage(f.getField(), f.getDefaultMessage())));
		
		return ResponseEntity.unprocessableEntity().body(error);
	}
}






