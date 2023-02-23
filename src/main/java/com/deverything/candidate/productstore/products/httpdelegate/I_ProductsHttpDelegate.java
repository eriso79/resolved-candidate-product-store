package com.deverything.candidate.productstore.products.httpdelegate;

import java.util.List;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;
import com.deverything.candidate.productstore.products.model.ProductObject;

public interface I_ProductsHttpDelegate {

	

	
	List<ProductObject> allproduts() throws ProductstoreHttpClienException, ProductstoreHttpException;

	ProductObject byProdId(int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException;

	ProductDimensionsObject getDimensionsByIdProd(int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException;

}
