/**
 * 
 */
package com.aquaadmin.customer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aquaadmin.customer.model.AquaLocation;
import com.aquaadmin.customer.model.Customer;
import com.aquaadmin.customer.repo.AquaAdminRepo;

/**
 * @author chaitanyaarava
 *
 */
@Service
public class AquaAdminServiceImpl implements AquaAdminService {

	private AquaAdminRepo aquaAdminRepo;
	
	public AquaAdminServiceImpl(AquaAdminRepo aquaAdminRepo) {
		this.aquaAdminRepo = aquaAdminRepo;
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = aquaAdminRepo.save(customer);
		
		for(AquaLocation aquaLocation: savedCustomer.getAquaLocations())  {
			aquaLocation.setCustomer(new Customer());
			aquaLocation.getCustomer().setCustomerId(savedCustomer.getCustomerId());
			aquaLocation.getCustomer().setCustomerType(savedCustomer.getCustomerType());
			aquaLocation.getCustomer().setFullName(savedCustomer.getEmailId());
			aquaLocation.getCustomer().setEmailId(savedCustomer.getEmailId());
			aquaLocation.getCustomer().setPhoneNumber(savedCustomer.getPhoneNumber());
		}
		
		Customer savedAquaCustomer = aquaAdminRepo.save(savedCustomer);
		
		return savedAquaCustomer;
	}

	@Override
	public Optional<Customer> getCustomerById(Long custId) {
		Optional<Customer> customer = aquaAdminRepo.findById(custId);
		return customer;
	}
}
