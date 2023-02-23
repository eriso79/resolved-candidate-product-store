package com.deverything.candidate.productstore.httpclient;

import java.io.Serializable;

import org.springframework.http.HttpMethod;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;

public interface I_ProductstoreHttpClient {

	<T> T send(HttpMethod method, String uri, Serializable reqBody, Class<T> clazzconvert)
			throws ProductstoreHttpClienException, ProductstoreHttpException, ProductstoreException;

}
