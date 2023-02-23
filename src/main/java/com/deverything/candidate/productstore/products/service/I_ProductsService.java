package com.deverything.candidate.productstore.products.service;

import java.util.List;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.products.model.ProductObject;

public interface I_ProductsService {

	List<ProductObject> AllProducts() throws ProductstoreHttpClienException, ProductstoreHttpException;

	ProductObject byProdId(int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException;

}
