package com.deverything.candidate.productstore.common.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BuilderReplyOnException {
	
	
		
	//for all exception
    public ReplyError  replyOnException(Exception e, HttpStatus httpStatus, WebRequest webRequest){
    	log.error("TRACK-EXCEPTION] url:{}",((ServletWebRequest)webRequest).getRequest().getRequestURI());
    	log.error("TRACK-EXCEPTION] httpStatus:{}",httpStatus);
    	log.error("TRACK-EXCEPTION] stacktrace:",e);
		return new ReplyError("an unexpected error has occurred");
    	
    }
    
    //for PoductstoreException or subtypes
    public ReplyError replyOnException(ProductstoreHttpException e, HttpStatus httpStatus, WebRequest webRequest){
    	log.error("TRACK-PoductstoreException] url:{}",((ServletWebRequest)webRequest).getRequest().getRequestURI());
    	log.error("TRACK-PoductstoreException] httpStatus:{}",httpStatus);
    	log.error("TRACK-PoductstoreException] stacktrace:",e);
		return new ReplyError(e.getMessage());
    	
    }
	
	
        
}
