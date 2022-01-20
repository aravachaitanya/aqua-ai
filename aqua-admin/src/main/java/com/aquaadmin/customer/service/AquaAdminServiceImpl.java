/**
 * 
 */
package com.aquaadmin.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquaadmin.customer.model.AquaLocation;
import com.aquaadmin.customer.model.AquaLogin;
import com.aquaadmin.customer.model.Customer;
import com.aquaadmin.customer.repo.AquaLocationRepo;
import com.aquaadmin.customer.repo.AquaLoginRepo;
import com.aquaadmin.customer.repo.CustomerRepo;

/**
 * @author chaitanyaarava
 *
 */
@Service
public class AquaAdminServiceImpl implements AquaAdminService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private AquaLoginRepo aquaLoginRepo;
	
	@Autowired
	private AquaLocationRepo aquaLocationRepo;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = customerRepo.save(customer);
		
		return savedCustomer;
	}

	@Override
	public Optional<Customer> getCustomerById(Long custId) {
		Optional<Customer> customer = customerRepo.findById(custId);
		return customer;
	}

	@Override
	public AquaLogin saveAquaLoginDetails(AquaLogin aquaLogin) {

		AquaLogin savedAquaLogin = aquaLoginRepo.save(aquaLogin);
		
		Customer customer = new Customer();
		customer.setCustomerId(savedAquaLogin.getUserId());
		customerRepo.save(customer);
		
		AquaLocation aquaLocation = new AquaLocation();
		aquaLocation.setCustomerId(savedAquaLogin.getUserId());
		aquaLocationRepo.save(aquaLocation);
		
		return savedAquaLogin;
	}
}
