package com.deverything.candidate.productstore.checkout.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deverything.candidate.productstore.checkout.httpdelegate.I_CheckoutHttpDelegate;
import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.valiator.I_CustomValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CheckoutService implements I_CheckoutService{
	
	
	private I_CheckoutHttpDelegate  delegate;
	
	
	private I_CustomValidator validator;
	
	@Override
	public CheckoutResponse doCheckout(CheckoutRequest req) throws ProductstoreHttpClienException, ProductstoreHttpException {
		validator.valid(req,HttpStatus.BAD_REQUEST);
		return this.delegate.doCheckout(req);
	}

}
