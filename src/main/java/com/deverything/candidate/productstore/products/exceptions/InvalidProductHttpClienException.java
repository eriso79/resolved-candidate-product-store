package com.deverything.candidate.productstore.products.exceptions;

import org.springframework.http.HttpStatus;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public class InvalidProductHttpClienException extends ProductstoreHttpException {

	
	
	
	public InvalidProductHttpClienException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}
	
	
	public InvalidProductHttpClienException() {
		super("",HttpStatus.valueOf(409));
	}


	
	
	
	
 
}
