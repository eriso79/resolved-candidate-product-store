package com.deverything.candidate.productstore.products.service;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;

public interface I_ProductDimensionsService {

	ProductDimensionsObject getProductDimensionsByIdProd(int prodId)
			throws ProductstoreHttpClienException, ProductstoreHttpException;

}
