package com.aquaadmin.customer.service;

import com.aquaadmin.customer.model.Customer;

/**
 * @author chaitanyaarava
 *
 */
public interface AquaAdminService {

	/**
	 * This methos is used to save customer details to dta base.
	 * 
	 * @param customer
	 * @return
	 */
	public Customer saveCustomer(Customer customer);

	/**
	 * This methos is to fetch customer details from database.
	 * @param custId
	 * @return
	 */
	public Customer getCustomerById(Long custId);
	
}
