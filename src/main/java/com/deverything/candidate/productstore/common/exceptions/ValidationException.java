package com.deverything.candidate.productstore.common.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends ProductstoreHttpException {

	public ValidationException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}
	
	
}
