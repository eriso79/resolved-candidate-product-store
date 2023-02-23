package com.deverything.candidate.productstore.boxes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.boxes.model.DimensionListObject;
import com.deverything.candidate.productstore.boxes.services.I_BoxesServices;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/")
@AllArgsConstructor
public class BoxesController {

	I_BoxesServices boxesServices;
	
	@GetMapping(value="/getBoxess")
	@ResponseBody public List<BoxObject> getBoxes() throws ProductstoreHttpClienException, ProductstoreHttpException  {
		return this.boxesServices.getAllBoxes();
	}
	
	@PostMapping(value="/searchBoxForWrapping")
	@ResponseBody
	public BoxObject searchBoxForWrapping(@RequestBody DimensionListObject dimList) throws ProductstoreHttpClienException, ProductstoreHttpException{
		return boxesServices.findBoxByListDimensions(dimList.getDimsList());
		
	}
	
}
