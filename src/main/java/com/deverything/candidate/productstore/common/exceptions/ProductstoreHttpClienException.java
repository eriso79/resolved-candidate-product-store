package com.deverything.candidate.productstore.common.exceptions;

import org.springframework.http.HttpStatus;

public class ProductstoreHttpClienException extends ProductstoreHttpException {

	
	
	
	public ProductstoreHttpClienException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}


	
	
	
 
}
