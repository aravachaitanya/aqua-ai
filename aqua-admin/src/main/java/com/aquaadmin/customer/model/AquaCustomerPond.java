package com.aquaadmin.customer.model;

import lombok.Data;

@Data
public class AquaCustomerPond {
	
	private String pondNumber;
	
	private double pondSize;
	
	private String unitOfMeasure;
	
	private String feedingType;
	
	private boolean status;
	
	private AquaCustomerLocation aquaCustomerLocation;
}
