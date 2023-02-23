package com.deverything.candidate.productstore.products.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.valiator.I_CustomValidator;
import com.deverything.candidate.productstore.products.exceptions.NoFoundProductHttpException;
import com.deverything.candidate.productstore.products.httpdelegate.I_ProductsHttpDelegate;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductDimensionsService implements I_ProductDimensionsService{
	
	private I_ProductsHttpDelegate productsHttpDelegate;
	
	private I_CustomValidator validator;
	
	@Override
	public
	ProductDimensionsObject getProductDimensionsByIdProd(int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException{
		ProductDimensionsObject dim=productsHttpDelegate.getDimensionsByIdProd(prodId);
		if (dim==null) {
			throw new NoFoundProductHttpException("product no found",HttpStatus.NOT_FOUND);
		}
		validator.valid(dim, HttpStatus.BAD_REQUEST);
		return dim;
		
	}

}
