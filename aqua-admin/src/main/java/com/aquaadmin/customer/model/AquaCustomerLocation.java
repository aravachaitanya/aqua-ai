package com.aquaadmin.customer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class AquaCustomerLocation {
	
	private String place;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private Date startDate;
	
	private int totalPonds;
	
	private String status;
	
	private Set<AquaCustomerPond> aquaCustomerPonds;

}
