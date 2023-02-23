package com.deverything.candidate.productstore.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.handlers.BuilderReplyOnException;
import com.deverything.candidate.productstore.common.handlers.ExceptionHandlers;
import com.deverything.candidate.productstore.common.handlers.ReplyError;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlersTest {

	@Mock
	private BuilderReplyOnException builderReplyOnException;
	
	
	@InjectMocks
	ExceptionHandlers exceptionHandlers;
	
	
	WebRequest webRequest;

	ReplyError replyError;
	
	@BeforeEach
	public void init() {
		//GIVEN
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setServerName("www.example.com");
		servletRequest.setRequestURI("/v1/someuri");
		 webRequest = new ServletWebRequest(servletRequest);
		 
		 this.replyError = new ReplyError("message from exception"); 
	}

	
	@Test
	public void handlePoductstoreExceptionTest() {
		//GIVEN
		ProductstoreHttpException ex= new ProductstoreHttpException("", HttpStatus.BAD_REQUEST);
		Mockito.when(builderReplyOnException.replyOnException(Mockito.any(ProductstoreHttpException.class), Mockito.any(HttpStatus.class), Mockito.any(WebRequest.class))).thenReturn(replyError);
		
		
		ResponseEntity<ReplyError> resp = exceptionHandlers.handlePoductstoreException(ex, webRequest);
		
		Assertions.assertTrue(resp.getBody().getMessage().equals("message from exception"));
		Assertions.assertTrue(resp.getStatusCode()== HttpStatus.BAD_REQUEST);
	}
	
	
	
	@Test
	public void handleExceptionTest() {
		//GIVEN
		Exception ex= new Exception("");
		Mockito.when(builderReplyOnException.replyOnException(Mockito.any(Exception.class), Mockito.any(HttpStatus.class), Mockito.any(WebRequest.class))).thenReturn(replyError);
		
		
		ResponseEntity<ReplyError> resp = exceptionHandlers.handleError(ex, webRequest);
		
		Assertions.assertTrue(resp.getBody().getMessage().equals("message from exception"));
		Assertions.assertTrue(resp.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
