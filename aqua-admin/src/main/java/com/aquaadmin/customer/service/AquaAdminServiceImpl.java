package com.aquaadmin.customer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aquaadmin.customer.entity.AquaLogin;
import com.aquaadmin.customer.entity.Customer;
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
	private PasswordEncoder passwordEncoder;
	
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
			customer.setAquaLogin(savedCustomer.get().getAquaLogin());
		}
		
		return customer;
	}

	@Override
	public boolean isAquaCustomerExists(String userName, String password) {

		AquaLogin findAquaLoginCustomer = aquaLoginRepo.findByUserNameAndUserPassword(userName, password);
		
		boolean isPasswordMatched = false;
		
		if(findAquaLoginCustomer != null && findAquaLoginCustomer.getUserPassword() != null ) {
			isPasswordMatched = passwordEncoder.matches(password, findAquaLoginCustomer.getUserPassword());
		}
		
		return isPasswordMatched;
	}

	@Override
	public Customer getCustomerByIdUserNameAndPassword(String userName, String password) {

		AquaLogin findAquaLoginCustomer = aquaLoginRepo.findByUserNameAndUserPassword(userName, password);

		Customer customer = null;

		if (findAquaLoginCustomer !=  null && findAquaLoginCustomer.getCustomer() != null) {
			customer = findAquaLoginCustomer.getCustomer();
		}

		return customer;
	}

}
