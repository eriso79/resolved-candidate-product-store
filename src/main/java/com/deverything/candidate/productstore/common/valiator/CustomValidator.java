package com.deverything.candidate.productstore.common.valiator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.deverything.candidate.productstore.common.exceptions.ValidationException;

@Component

public class CustomValidator implements I_CustomValidator {

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	 
	 @Override
	 public <T> void  valid(T toValidate, HttpStatus status ) throws ValidationException{
		Set<ConstraintViolation<T>> violations = this.validator.validate(toValidate);
		if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
                sb.append(" ");
            }
            
            throw new ValidationException("Error occurred: " + sb.toString(),status);
        }
	 }
	 
	 
	 @Override
	 public <T> void  valid(List<T> toValidate, HttpStatus status ) throws ValidationException{
		if (toValidate== null || toValidate.isEmpty()){
			return ;
		}
		for (T v:toValidate) {
			this.valid(v, status);
		}
	 }
	    
	 
}
