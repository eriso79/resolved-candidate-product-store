package com.deverything.candidate.productstore.checkout.service;

import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public interface I_CheckoutService {

	CheckoutResponse doCheckout(CheckoutRequest req) throws ProductstoreHttpClienException, ProductstoreHttpException;

}
