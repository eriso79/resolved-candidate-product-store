package com.deverything.candidate.productstore.checkout.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.checkout.service.I_CheckoutService;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/")
@AllArgsConstructor
public class CheckoutController {
	
	
	private I_CheckoutService service;
	
	@PostMapping(value="/checkout")
	@ResponseBody
	public CheckoutResponse searchBoxForWrapping(@RequestBody CheckoutRequest req) throws ProductstoreHttpClienException, ProductstoreHttpException{
		return service.doCheckout(req);
		
	}
	

}
