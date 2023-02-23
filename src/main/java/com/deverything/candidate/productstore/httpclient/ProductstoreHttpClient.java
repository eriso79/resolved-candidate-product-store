package com.deverything.candidate.productstore.httpclient;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ProductstoreHttpClient implements I_ProductstoreHttpClient{
	
	private RestTemplate restTemplate;
	
	@Value("${USER_HEADER_NAME}")
	private String userHeaderName;

	
	@Value("${USER_HEADER_VALUE}")
	private String userHeaderValue;
	
	
	@Value("${APIKEY_HEADER_NAME}")
	private String apikeyHeaderName;
	
	@Value("${APIKEY_HEADER_VALUE}")
	private String apikeyHeaderValue;
	
	
	public ProductstoreHttpClient(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}





	@Override
	public <T> T send(HttpMethod method, String uri, Serializable reqBody, Class<T> clazzconvert) throws ProductstoreHttpClienException{
		MultiValueMap<String, String> header= new LinkedMultiValueMap<>();
		try {
			header.add(userHeaderName, userHeaderValue);
			header.add(apikeyHeaderName, apikeyHeaderValue);
			
			ResponseEntity<T> resp = this.restTemplate.exchange(uri,  method,new HttpEntity<>(reqBody,header),clazzconvert);
			return resp.getBody();
		}catch (Exception e) {
			handleError(e);
			throw new ProductstoreHttpClienException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	
	
	
	
	private void handleError(Exception e) throws ProductstoreHttpClienException {
		if (e instanceof RestClientResponseException) {
			RestClientResponseException re = (RestClientResponseException)e;
			throw new ProductstoreHttpClienException(re.getResponseBodyAsString(),HttpStatus.valueOf(re.getRawStatusCode()));
		}
	}

}
