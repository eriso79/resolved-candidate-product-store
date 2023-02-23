package com.deverything.candidate.productstore.products.model;

import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDimensionsObject {
	
	private String statusCode;
	@DecimalMin(inclusive = false, value = "0", message = "width must be gather than 0")
	private double width;
	@DecimalMin(inclusive = false, value = "0", message = "height must be gather than 0")
	private double height;


}
