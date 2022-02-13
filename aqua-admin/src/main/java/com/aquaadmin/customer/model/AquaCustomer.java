package com.aquaadmin.customer.model;

import java.util.Date;

import lombok.Data;

@Data
public class AquaCustomer {

	private String fullName;
	
	private String emailId;
	
	private Long PhoneNumber;
	
	private String customerType;
	
	private Date startDate; 
	
	private AquaCustomerLogin aquaCustomerLogin;
	
}