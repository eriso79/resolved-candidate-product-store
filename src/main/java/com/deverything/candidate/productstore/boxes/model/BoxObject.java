package com.deverything.candidate.productstore.boxes.model;

import javax.validation.constraints.DecimalMin;
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
public class BoxObject {
	
	
	@NotNull(message= "id-box cannot be null")
	 private  Integer id;
	@DecimalMin(inclusive = false, value = "0", message = "box-width must be gather than 0")
	private double width;
	@DecimalMin(inclusive = false, value = "0", message = "box-height must be gather than 0")
	private double height;


}
