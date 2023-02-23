package com.deverything.candidate.productstore.products.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ValidationException;
import com.deverything.candidate.productstore.common.valiator.I_CustomValidator;
import com.deverything.candidate.productstore.products.exceptions.NoFoundProductHttpException;
import com.deverything.candidate.productstore.products.exceptions.InvalidProductHttpClienException;
import com.deverything.candidate.productstore.products.httpdelegate.I_ProductsHttpDelegate;
import com.deverything.candidate.productstore.products.model.ProductObject;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsService implements I_ProductsService {
	
	
	private I_ProductsHttpDelegate productsHttpDelegate;
	
	private I_CustomValidator validator;
	
	@Override
	public List<ProductObject> AllProducts() throws ProductstoreHttpClienException, ProductstoreHttpException {
		List<ProductObject> list = productsHttpDelegate.allproduts();
		for (ProductObject prod:list) {
			try {	
			   validator.valid(prod,HttpStatus.BAD_REQUEST );
			} catch(ValidationException e) {
				list.remove(prod);
			}
		}
		return list;
	}
	
	
	@Override
	public ProductObject byProdId( int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductObject prod = productsHttpDelegate.byProdId(prodId);
		if (prod==null) {
			throw new NoFoundProductHttpException("product no found",HttpStatus.NOT_FOUND);
		}
		validator.valid(prod,HttpStatus.BAD_REQUEST );
		return prod;
		
	}


}
