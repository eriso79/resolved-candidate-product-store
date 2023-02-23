package com.deverything.candidate.productstore.boxes.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoxListObject  {
	
	 private String statusCode;
	 private List<BoxObject> boxes;

}
