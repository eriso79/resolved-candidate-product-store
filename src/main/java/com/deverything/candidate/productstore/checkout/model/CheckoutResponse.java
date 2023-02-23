package com.deverything.candidate.productstore.checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CheckoutResponse {
	
	private String statusCode;
	private String result;

}
