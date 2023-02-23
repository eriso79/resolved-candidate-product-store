package com.deverything.candidate.productstore.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.handlers.BuilderReplyOnException;
import com.deverything.candidate.productstore.common.handlers.ReplyError;

@ExtendWith(MockitoExtension.class)
public class BuilderReplyOnExceptionTest {

	@InjectMocks
	BuilderReplyOnException builderReplyOnException;

	WebRequest webRequest;

	@BeforeEach
	public void init() {
		//GIVEN
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setServerName("www.example.com");
		servletRequest.setRequestURI("/v1/someuri");
		 webRequest = new ServletWebRequest(servletRequest);
	}

	@Test
	public void replyOnExceptionTest() {
		
		//WHEN
		ReplyError reply=builderReplyOnException.replyOnException(new Exception(), HttpStatus.FORBIDDEN, webRequest);
		
		//THEN
		Assertions.assertTrue(reply!= null);
		Assertions.assertTrue("an unexpected error has occurred".equals(reply.getMessage()));
		
	}
	
	
	
	@Test
	public void replyOnPoductstoreExceptionTest() {
		
		ProductstoreHttpException ex= new ProductstoreHttpException("message from exception", HttpStatus.INTERNAL_SERVER_ERROR);
		//WHEN
		ReplyError reply=builderReplyOnException.replyOnException(ex, HttpStatus.FORBIDDEN, webRequest);
		
		//THEN
		Assertions.assertTrue(reply!= null);
		Assertions.assertTrue("message from exception".equals(reply.getMessage()));
	}

}
