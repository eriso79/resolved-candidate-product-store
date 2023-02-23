package com.deverything.candidate.productstore.boxes.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deverything.candidate.productstore.boxes.exception.InvalidDimensionsHttpException;
import com.deverything.candidate.productstore.boxes.exception.NoFoundBoxHttpException;
import com.deverything.candidate.productstore.boxes.httpdelegate.I_BoxesHttpDelegate;
import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.boxes.model.DimensionObject;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.common.exceptions.ValidationException;
import com.deverything.candidate.productstore.common.valiator.I_CustomValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoxesServices implements I_BoxesServices{
	
	
	private static final double PLUS=0.5;
	
	private I_BoxesHttpDelegate boxesDelegate;
	
	private I_CustomValidator validator;
	


	@Override
	public List<BoxObject> getAllBoxes() throws ProductstoreHttpClienException, ProductstoreHttpException{
		List<BoxObject> resp = boxesDelegate.allBoxes();
		for (BoxObject box :resp)
		try {
		       this.validator.valid(box, HttpStatus.BAD_REQUEST);
		} catch (ValidationException e){
		    	resp.remove(box);
		}
		if (resp.isEmpty()) {
			throw new NoFoundBoxHttpException("boxes dosen't exist ",HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	
	
	
	
	@Override
	public  BoxObject findBoxByDimensions(List<BoxObject> allBoxes ,DimensionObject dim) throws ProductstoreHttpClienException, ProductstoreHttpException {
		double widthfit=dim.getWidth()+PLUS;
		double heighfit=dim.getHeigh()+PLUS;
		
		
		 // all of them fit 
		  List<BoxObject> allFit = allBoxes.stream()
				.filter( box->box.getHeight() >= heighfit &&  box.getWidth()>=widthfit)
				.collect(Collectors.toList());
				
		
		if (allFit==null || allFit.isEmpty() ) {
			throw new NoFoundBoxHttpException("Box does not found",HttpStatus.NOT_FOUND);
		}
		// find out min height
		Optional<BoxObject> minHeight = allFit.stream().sorted(Comparator.comparing(BoxObject::getHeight)).findFirst();
		// find out min width
		Optional<BoxObject> minWidth = allFit.stream().sorted(Comparator.comparing(BoxObject::getWidth)).findFirst();
		
		//  find out min bewtwem height and  width  
		if (minHeight.get().getHeight()<minWidth.get().getWidth()) {
			return minHeight.get();
		}else if (minHeight.get().getHeight()==minWidth.get().getWidth()) {
			return minHeight.get();
		}else {
			return minWidth.get();
		}
		
	}
	
	
	@Override
	public  BoxObject findBoxByListDimensions(List<DimensionObject> dim) throws ProductstoreHttpClienException, ProductstoreHttpException {
		
		 List<BoxObject> allBoxes = this.getAllBoxes();
		if (dim==null || dim.isEmpty()) {
			 throw new InvalidDimensionsHttpException("invalid dimensions",HttpStatus.BAD_REQUEST);
		 }
		 DimensionObject dimtotal= new DimensionObject();
		 for (DimensionObject d:dim) {
			   dimtotal.setHeigh(dimtotal.getHeigh()+d.getHeigh());
			   dimtotal.setWidth(dimtotal.getWidth()+d.getWidth());
		 }
		 return this.findBoxByDimensions(allBoxes,dimtotal);
		 
	}

}
