package com.deverything.candidate.productstore.common.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductstoreHttpException extends Exception{

	
	private HttpStatus httpStatus;
	
	
	private String message;
	
	public ProductstoreHttpException(String message, HttpStatus httpStatus ) {
		this.message=message;
		this.httpStatus=httpStatus;
		// TODO Auto-generated constructor stub
	}

	
	
}
