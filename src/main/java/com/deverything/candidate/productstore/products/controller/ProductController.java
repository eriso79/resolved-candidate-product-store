package com.deverything.candidate.productstore.products.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;
import com.deverything.candidate.productstore.products.model.ProductObject;
import com.deverything.candidate.productstore.products.service.I_ProductDimensionsService;
import com.deverything.candidate.productstore.products.service.I_ProductsService;

import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/")
@AllArgsConstructor
public class ProductController {

	private I_ProductsService productsService;
	
	private I_ProductDimensionsService  productDimensionsService;
	
	@GetMapping(value="/getProducts")
	@ResponseBody public List<ProductObject> getProducts() throws ProductstoreHttpClienException, ProductstoreHttpException {
		return this.productsService.AllProducts();
	}
	
	
	@GetMapping(value="/getProduct/{prodId}")
	@ResponseBody public ProductObject byProdId(@PathVariable int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException {
		return this.productsService.byProdId(prodId);
	}
	
	
	@GetMapping(value="/getDimensions/{prodId}")
	@ResponseBody public ProductDimensionsObject dimensionsbyProdId(@PathVariable int prodId) throws ProductstoreHttpClienException, ProductstoreHttpException {
		return this.productDimensionsService.getProductDimensionsByIdProd(prodId);
	}

	
	
	
}
