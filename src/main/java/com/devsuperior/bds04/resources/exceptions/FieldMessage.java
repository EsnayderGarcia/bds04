package com.devsuperior.bds04.resources.exceptions;

import java.io.Serializable;

public class FieldMessage  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage(String fiedName, String message) {
		this.fieldName = fiedName;
		this.message = message;
	}

	public String getfieldName() {
		return fieldName;
	}

	public void setField(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
