package com.deverything.candidate.productstore.checkout.httpdelegate;

import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public interface I_CheckoutHttpDelegate {

	CheckoutResponse doCheckout(CheckoutRequest request)
			throws ProductstoreHttpClienException, ProductstoreHttpException;
	


}
