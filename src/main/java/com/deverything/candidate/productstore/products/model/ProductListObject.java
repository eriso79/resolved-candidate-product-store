package com.deverything.candidate.productstore.products.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductListObject implements Serializable {
	
	 private String statusCode;
	 private List<ProductObject> products;

}
