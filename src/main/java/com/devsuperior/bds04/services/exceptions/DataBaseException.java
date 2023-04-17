package com.devsuperior.bds04.services.exceptions;

import java.io.Serial;

public class DataBaseException extends RuntimeException {
	public DataBaseException(String message) {
		super(message);
	}
}
