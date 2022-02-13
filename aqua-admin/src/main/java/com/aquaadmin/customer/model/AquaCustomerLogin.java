package com.aquaadmin.customer.model;

import java.util.Date;

import lombok.Data;

@Data
public class AquaCustomerLogin {

	private String userName;
	
	private String userPassword;
	
	private boolean userStatus;
	
	private Date userStartDate;
	
	private Date userEndDate;

	private AquaCustomer aquaCustomer;
}
