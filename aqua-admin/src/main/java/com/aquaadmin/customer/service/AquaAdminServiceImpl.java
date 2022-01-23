/**
 * 
 */
package com.aquaadmin.customer.service;

import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Transactional
	@Override
	public Customer saveCustomer(Customer customer) {

		Customer savedCustomer = customerRepo.save(customer);
		
		AquaLogin aquaLogin = savedCustomer.getAquaLogin();
		
		Customer unSaveCustomer = new Customer();
		unSaveCustomer.setCustomerId(savedCustomer.getCustomerId());
		
		aquaLogin.setCustomer(unSaveCustomer);
		
		return savedCustomer;
	}

	@Override
	public Customer getCustomerById(Long custId) {
		Optional<Customer> savedCustomer = customerRepo.findById(custId);
		
		Customer customer = null;
		
		if(savedCustomer.isPresent()) {
		
			customer = new Customer();
			
			customer.setCustomerId(savedCustomer.get().getCustomerId());
			customer.setCustomerType(savedCustomer.get().getCustomerType());
			customer.setEmailId(savedCustomer.get().getEmailId());
			customer.setFullName(savedCustomer.get().getFullName());
			customer.setPhoneNumber(savedCustomer.get().getPhoneNumber());
			customer.setStartDate(savedCustomer.get().getStartDate());
		}
		
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
