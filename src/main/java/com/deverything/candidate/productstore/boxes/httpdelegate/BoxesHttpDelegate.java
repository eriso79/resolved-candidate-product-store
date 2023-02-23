package com.deverything.candidate.productstore.boxes.httpdelegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.deverything.candidate.productstore.boxes.exception.NoFoundBoxHttpException;
import com.deverything.candidate.productstore.boxes.model.BoxListObject;
import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.httpclient.ProductstoreHttpClient;
import com.deverything.candidate.productstore.products.exceptions.NoFoundProductHttpException;


@Component
public class BoxesHttpDelegate implements I_BoxesHttpDelegate{
	
	
	@Value("${URL_ALL_BOXES}")
	private String uriAllBoxes;
	
	
     private ProductstoreHttpClient client;
	
	public BoxesHttpDelegate(ProductstoreHttpClient client) {
		this.client = client;
	}

	
	
	@Override
	public List<BoxObject>  allBoxes() throws ProductstoreHttpClienException, ProductstoreHttpException {
		BoxListObject allBoxes=client.send(HttpMethod.GET, uriAllBoxes, null, BoxListObject.class);
		if (allBoxes==null) {
			throw new NoFoundBoxHttpException("boxes dosen't exist ",HttpStatus.NOT_FOUND);
		}else if (allBoxes.getBoxes()== null || allBoxes.getBoxes().isEmpty() ){
			throw new NoFoundBoxHttpException("boxes dosen't exist ",HttpStatus.NOT_FOUND);
		}
		return allBoxes.getBoxes();
	}
	
	
	

}
