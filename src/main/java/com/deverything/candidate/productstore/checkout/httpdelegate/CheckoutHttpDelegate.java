package com.deverything.candidate.productstore.checkout.httpdelegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.deverything.candidate.productstore.boxes.exception.NoFoundBoxHttpException;
import com.deverything.candidate.productstore.boxes.model.BoxListObject;
import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.checkout.exception.CheckoutHttpException;
import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.httpclient.ProductstoreHttpClient;

@Component
public class CheckoutHttpDelegate implements I_CheckoutHttpDelegate{
	
	@Value("${URL_CHECKOUT}")
	private String urlcheckout;
	
	
	private ProductstoreHttpClient client;
	
	public CheckoutHttpDelegate(ProductstoreHttpClient client) {
		this.client = client;
	}
	
	
	
	@Override
	public CheckoutResponse doCheckout(CheckoutRequest request) throws ProductstoreHttpClienException, ProductstoreHttpException {
		CheckoutResponse checkrespo=client.send(HttpMethod.POST, urlcheckout, request, CheckoutResponse.class);
		if (checkrespo==null) {
			throw new CheckoutHttpException("checkout error ",HttpStatus.NOT_FOUND);
		}
		return checkrespo;
	}
	


}
