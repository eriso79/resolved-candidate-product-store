package com.deverything.candidate.productstore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.boxes.model.DimensionListObject;
import com.deverything.candidate.productstore.boxes.model.DimensionObject;
import com.deverything.candidate.productstore.checkout.model.CheckoutRequest;
import com.deverything.candidate.productstore.checkout.model.CheckoutResponse;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.products.controller.ProductController;
import com.deverything.candidate.productstore.products.model.ProductDimensionsObject;
import com.deverything.candidate.productstore.products.model.ProductObject;
import com.deverything.candidate.productstore.products.service.I_ProductDimensionsService;
import com.deverything.candidate.productstore.products.service.I_ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApiServiceTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	private ObjectMapper objectMapper;

	@Mock
	private I_ProductsService productsService;

	@Mock
	private I_ProductDimensionsService productDimensionsService;

	@InjectMocks
	private ProductController productController;

	List<ProductObject> listProd;

	ProductDimensionsObject productDimensionsObject3;

	ProductDimensionsObject productDimensionsObject7;
	
	DimensionObject dimensionObject7;
	
	
	DimensionObject dimensionObject3;

	private List<ProductObject> getAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<ProductObject[]> responseEntity = restTemplate.getForEntity(createURLWithPort("/getProducts"),
				ProductObject[].class);
		Assertions.assertTrue(responseEntity.getStatusCodeValue() == 200);
		ProductObject[] array = responseEntity.getBody();
		return Stream.of(array).collect(Collectors.toCollection(ArrayList::new));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@BeforeEach
	public void init() throws ProductstoreHttpClienException, ProductstoreHttpException {
		this.listProd = getAllProducts();
		productDimensionsObject3 = new ProductDimensionsObject("OK", 10, 50);
		productDimensionsObject7 = new ProductDimensionsObject("OK", 90, 100);
		dimensionObject7= new DimensionObject(productDimensionsObject7.getWidth(), productDimensionsObject7.getHeight());
		dimensionObject3= new DimensionObject(productDimensionsObject3.getWidth(), productDimensionsObject3.getHeight());
		Mockito.when(productController.dimensionsbyProdId(3)).thenReturn(productDimensionsObject3);
		Mockito.when(productController.dimensionsbyProdId(7)).thenReturn(productDimensionsObject7);
	}

	@Test
	public void dimensionsProd3() throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductDimensionsObject dimProd = productController.dimensionsbyProdId(3);
		Assertions.assertTrue(dimProd.getHeight() == productDimensionsObject3.getHeight());
		Assertions.assertTrue(dimProd.getWidth() == productDimensionsObject3.getWidth());
		Assertions.assertTrue(dimProd.getStatusCode() == productDimensionsObject3.getStatusCode());

	}

	@Test
	public void dimensionsProd7() throws ProductstoreHttpClienException, ProductstoreHttpException {
		ProductDimensionsObject dimProd = productController.dimensionsbyProdId(7);
		Assertions.assertTrue(dimProd.getHeight() == productDimensionsObject7.getHeight());
		Assertions.assertTrue(dimProd.getWidth() == productDimensionsObject7.getWidth());
		Assertions.assertTrue(dimProd.getStatusCode() == productDimensionsObject7.getStatusCode());

	}

	@Test
	public void productsGratherThan300() {
		log.info("products Grather Than are: ");
		this.listProd.stream().filter(p -> p.getPrice() > 300).forEach(r -> log.info("prodid:{}", r.getId()));
	}

	@Test
	public void prodAll() {
		log.info("all products are:{}",listProd);
		Assertions.assertTrue(listProd!= null && !listProd.isEmpty());
	}
	
	
	@Test
	public void findBoxByDimensionsObject() {
		log.info("all products are:{}",listProd);
		DimensionObject dim1 = new DimensionObject(productDimensionsObject3.getWidth(),productDimensionsObject3.getHeight());
		DimensionObject dim2 = new DimensionObject(productDimensionsObject7.getWidth(),productDimensionsObject7.getHeight());
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<BoxObject> responseEntity = restTemplate.postForEntity(createURLWithPort("/searchBoxForWrapping"),
				new DimensionListObject(List.of(dim1,dim2)),
				BoxObject.class);
		
		
		log.info("Boxes found:{}",responseEntity.getBody());
		Assertions.assertTrue(responseEntity.getStatusCodeValue() == 200);
		
		
		
	}
	
	@Test
	public void  checkoutTesd() throws ProductstoreHttpClienException {
		log.info("all products are:{}",listProd);
		DimensionObject dim1 = new DimensionObject(productDimensionsObject3.getWidth(),productDimensionsObject3.getHeight());
		DimensionObject dim2 = new DimensionObject(productDimensionsObject7.getWidth(),productDimensionsObject7.getHeight());
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<BoxObject> responseEntity = restTemplate.postForEntity(createURLWithPort("/searchBoxForWrapping"),
				new DimensionListObject(List.of(dim1,dim2)),
				BoxObject.class);
		Assertions.assertTrue(responseEntity.getStatusCodeValue() == 200);
		BoxObject box = responseEntity.getBody();
		
		CheckoutRequest req = new CheckoutRequest();
		req.setBoxId(box.getId());
		
		ResponseEntity<CheckoutResponse> responseEntityCheckout = restTemplate.postForEntity(createURLWithPort("/checkout"),
				req,
				CheckoutResponse.class);
		Assertions.assertTrue(responseEntityCheckout.getStatusCodeValue() != 200);
		
		
		
	}
	
	

	
		
		
		
	

}