package com.deverything.candidate.productstore.checkout.exception;

import org.springframework.http.HttpStatus;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public class CheckoutHttpException extends ProductstoreHttpException {

	
	public CheckoutHttpException(String message, HttpStatus httpStatus ) {
		super(message,httpStatus);
	}


	
	
	
 
}
