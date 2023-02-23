package com.deverything.candidate.productstore.boxes.services;

import java.util.List;

import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.boxes.model.DimensionObject;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;

public interface I_BoxesServices {

	List<BoxObject> getAllBoxes() throws ProductstoreHttpClienException, ProductstoreHttpException;

	BoxObject findBoxByListDimensions(List<DimensionObject> dim)
			throws ProductstoreHttpClienException, ProductstoreHttpException;

	BoxObject findBoxByDimensions(List<BoxObject> allBoxes, DimensionObject dim)
			throws ProductstoreHttpClienException, ProductstoreHttpException;

}
