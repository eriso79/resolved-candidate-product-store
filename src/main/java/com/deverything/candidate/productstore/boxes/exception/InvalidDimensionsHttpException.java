package com.deverything.candidate.productstore.boxes.exception;

import org.springframework.http.HttpStatus;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public class InvalidDimensionsHttpException extends ProductstoreHttpException {

	
	public InvalidDimensionsHttpException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}


	
	
	
 
}
