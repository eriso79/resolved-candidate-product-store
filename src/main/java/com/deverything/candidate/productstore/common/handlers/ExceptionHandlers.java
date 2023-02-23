package com.deverything.candidate.productstore.common.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionHandlers {

	private BuilderReplyOnException builderReplyOnException;


       @ResponseBody
	   @ExceptionHandler(ProductstoreHttpException.class)
       public ResponseEntity<ReplyError> handlePoductstoreException(ProductstoreHttpException ex, WebRequest request) {
	        return  new ResponseEntity<ReplyError>(builderReplyOnException.replyOnException(ex,ex.getHttpStatus(),request),ex.getHttpStatus());
	
	    }

	
       @ResponseBody
	   @ExceptionHandler(Exception.class)
       public ResponseEntity<ReplyError> handleError(Exception ex, WebRequest request) {
	        return  new ResponseEntity<ReplyError>(builderReplyOnException.replyOnException(ex,HttpStatus.INTERNAL_SERVER_ERROR,request),HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
