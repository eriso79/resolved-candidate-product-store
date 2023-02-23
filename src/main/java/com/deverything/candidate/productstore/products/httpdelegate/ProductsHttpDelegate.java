package com.deverything.candidate.productstore.products.httpdelegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.httpclient.ProductstoreHttpClient;
import com.deverything.candidate.productstore.products.exceptions.NoFoundProductHttpException;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;
import com.deverything.candidate.productstore.products.model.ProductObject;
import com.deverything.candidate.productstore.products.model.ProductListObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductsHttpDelegate implements I_ProductsHttpDelegate{

	@Value("${URL_ALL_PRODUCTS}")
	private String uriAllProducts;
	
	
	@Value("${URL_BY_PRODUCT}")
	private String urlByProduct;
	
	@Value("${URL_DIM_BY_PRODUCT}")
	private String dimByProd;
	
	
	private ProductstoreHttpClient client;
	
	public ProductsHttpDelegate(ProductstoreHttpClient client) {
		this.client = client;
	}

	@Override
	public List<ProductObject>  allproduts() throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductListObject allprod=client.send(HttpMethod.GET, uriAllProducts, null, ProductListObject.class);
		if (allprod==null) {
			throw new NoFoundProductHttpException("products dosen't exist ",HttpStatus.NOT_FOUND);
		}else if (allprod.getProducts()== null || allprod.getProducts().isEmpty() ){
			throw new NoFoundProductHttpException("products dosen't exist ",HttpStatus.NOT_FOUND);
		}
		return allprod.getProducts();
	}
	
	
	
	@Override
	public ProductObject  byProdId( int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductObject allprod=client.send(HttpMethod.GET, urlByProduct+"/"+prodId, null, ProductObject.class);
		if (allprod==null) {
			throw new NoFoundProductHttpException("products dosen't exist ",HttpStatus.NOT_FOUND);
		}
		return allprod;
	}
	
	
	@Override
	public ProductDimensionsObject getDimensionsByIdProd(int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductDimensionsObject allprod=client.send(HttpMethod.GET, dimByProd+"/"+prodId, null, ProductDimensionsObject.class);
		if (allprod==null) {
			throw new NoFoundProductHttpException("products dosen't exist ",HttpStatus.NOT_FOUND);
		}
		return allprod;
		
	}
}


