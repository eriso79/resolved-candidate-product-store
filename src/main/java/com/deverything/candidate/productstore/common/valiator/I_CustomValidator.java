package com.deverything.candidate.productstore.common.valiator;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.deverything.candidate.productstore.common.exceptions.ValidationException;

public interface I_CustomValidator {

	<T> void valid(T toValidate, HttpStatus status) throws ValidationException;

	<T> void valid(List<T> toValidate, HttpStatus status) throws ValidationException;

}
