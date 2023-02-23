package com.deverything.candidate.productstore.boxes.httpdelegate;

import java.util.List;

import com.deverything.candidate.productstore.boxes.model.BoxObject;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpClienException;
import com.deverything.candidate.productstore.common.exceptions.ProductstoreHttpException;
import com.deverything.candidate.productstore.products.model.ProductObject;

public interface I_BoxesHttpDelegate {

	List<BoxObject> allBoxes() throws ProductstoreHttpClienException, ProductstoreHttpException;

}
