package com.deverything.candidate.productstore.products.exceptions;

import org.springframework.http.HttpStatus;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public class NoFoundProductHttpException extends ProductstoreHttpException {

	
	public NoFoundProductHttpException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}


	
	
	
 
}
