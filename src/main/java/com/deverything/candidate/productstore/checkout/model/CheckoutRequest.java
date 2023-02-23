package com.deverything.candidate.productstore.checkout.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutRequest implements Serializable {

	    @NotNull
	    public int boxId;
	    @NotNull
	    @NotEmpty
	    public List<Integer> productIds;
	

}
