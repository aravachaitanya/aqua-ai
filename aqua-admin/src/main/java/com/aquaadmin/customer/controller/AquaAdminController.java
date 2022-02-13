package com.aquaadmin.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aquaadmin.customer.entity.AquaLogin;
import com.aquaadmin.customer.entity.Customer;
import com.aquaadmin.customer.exception.AquaAdminException;
import com.aquaadmin.customer.exception.CustomerNotFoundException;
import com.aquaadmin.customer.exception.ErrorMessage;
import com.aquaadmin.customer.model.AquaCustomer;
import com.aquaadmin.customer.model.AquaCustomerLogin;
import com.aquaadmin.customer.model.AquaUser;
import com.aquaadmin.customer.service.AquaAdminService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author chaitanyaarava
 *
 */
@RestController
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class AquaAdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AquaAdminController.class);
	
	@Autowired
	private AquaAdminService aquaAdminService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * This method is to save customer data into user_admin and user_location tables.
	 * 
	 * @param aquaCustomer
	 * @return
	 * @throws AquaAdminException
	 */
	@PostMapping("/saveCustomer")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody AquaCustomer aquaCustomer) throws AquaAdminException {
		
		LOGGER.info("Before saving customer");
		
		encryptPassword(aquaCustomer);
		
		Customer customer = mapCustomer(aquaCustomer);
		
		Customer savedCustomer = aquaAdminService.saveCustomer(customer);
		
		LOGGER.info("Saved following customer:: " + savedCustomer.getCustomerId());
		
		
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
	}

	/**
	 * @param aquaCustomer
	 * @return
	 */
	private Customer mapCustomer(AquaCustomer aquaCustomer) {
		Customer customer = new Customer();
		customer.setFullName(aquaCustomer.getFullName());
		customer.setCustomerType(aquaCustomer.getCustomerType());
		customer.setEmailId(aquaCustomer.getEmailId());
		customer.setPhoneNumber(aquaCustomer.getPhoneNumber());
		customer.setStartDate(aquaCustomer.getStartDate());
		
		AquaLogin aquaLogin = new AquaLogin();
		
		AquaCustomerLogin aquaCustomerLogin = aquaCustomer.getAquaCustomerLogin();
		
		aquaLogin.setUserName(aquaCustomerLogin.getUserName());
		aquaLogin.setUserPassword(aquaCustomerLogin.getUserPassword());
		aquaLogin.setUserStartDate(aquaCustomerLogin.getUserStartDate());
		aquaLogin.setUserEndDate(aquaCustomerLogin.getUserEndDate());
		aquaLogin.setUserStatus(true);
		
		customer.setAquaLogin(aquaLogin);
		
		return customer;
	}
	
	/**
	 * This methos will fetch all the customer data from user_admin and user_location tables.
	 * @param customerId
	 * @return
	 */
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public ResponseEntity<Customer> fetchCustomer(
			@Valid
			@Min(value = 1, message = "customer Id is invalid")
			@Max(value = 99999999L, message= "customer Id is invalid")
			@PathVariable(value="customerId", required = true)
			Long customerId) {

		Customer customer = aquaAdminService.getCustomerById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(
					  "customerId not found: "+customerId, null
					);

		} else
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	} 
	
	/**
	 * This methos will check for login credentials for aqua customer.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping("/isUserExists")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Boolean.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public boolean isAquaUserExists(@RequestBody AquaUser aquaUser) {

		boolean aquaUserExists = aquaAdminService.isAquaCustomerExists(aquaUser.getUserName(), aquaUser.getUserPassword());
		return aquaUserExists;
	}
	
	/**
	 * This methos will check for login credentials for aqua customer.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@PostMapping("/customerByUser")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public ResponseEntity<Customer> getAquaCustomer(@RequestBody AquaUser aquaUser) {

		Customer aquaCustomer = aquaAdminService.getCustomerByIdUserNameAndPassword(aquaUser.getUserName(), aquaUser.getUserPassword());
		if (aquaCustomer == null) {
			throw new CustomerNotFoundException(
					  "customer Name not found: "+aquaUser.getUserName(), null
					);

		} else
			return new ResponseEntity<Customer>(aquaCustomer, HttpStatus.OK);
	}



	/**
	 * @param aquaCustomer
	 */
	private void encryptPassword(AquaCustomer aquaCustomer) {

		if (aquaCustomer.getAquaCustomerLogin() != null && aquaCustomer.getAquaCustomerLogin().getUserPassword() != null
				&& aquaCustomer.getAquaCustomerLogin().getUserPassword().length() > 0) {
			String userpwd = aquaCustomer.getAquaCustomerLogin().getUserPassword();
			String encryptedUserPassword = bCryptPasswordEncoder.encode(userpwd);
			aquaCustomer.getAquaCustomerLogin().setUserPassword(encryptedUserPassword);
		}

	}
}
