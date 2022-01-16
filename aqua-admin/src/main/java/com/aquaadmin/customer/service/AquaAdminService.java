/**
 * 
 */
package com.aquaadmin.customer.service;

import java.util.Optional;

import com.aquaadmin.customer.model.Customer;

/**
 * @author chaitanyaarava
 *
 */
public interface AquaAdminService {

	public Customer saveCustomer(Customer customer);

	public Optional<Customer> getCustomerById(Long custId);
}
