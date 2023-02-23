package com.deverything.candidate.productstore.products.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductObject {
	
	 @NotNull(message= "id-prod cannot be null")
	 private  Integer id;
	 @NotNull(message= "name-prod cannot be null")
	 @NotBlank(message = "name-prod  must not be blank")
	 private String name;
	 @NotNull(message= "price-prod cannot be null")
	 @Min(value = 0, message = "price-prod  should be greater than or equal 0")
	 private Double price;

}
