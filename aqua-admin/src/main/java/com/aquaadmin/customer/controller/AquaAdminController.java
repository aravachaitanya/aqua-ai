package com.aquaadmin.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aquaadmin.customer.exception.AquaAdminException;
import com.aquaadmin.customer.exception.CustomerNotFoundException;
import com.aquaadmin.customer.exception.ErrorMessage;
import com.aquaadmin.customer.model.Customer;
import com.aquaadmin.customer.service.AquaAdminService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author chaitanyaarava
 *
 */
@RestController
@Validated
public class AquaAdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AquaAdminController.class);
	
	@Autowired
	private AquaAdminService aquaAdminService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * This method is to save customer data into user_admin and user_location tables.
	 * 
	 * @param customer
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
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) throws AquaAdminException {
		
		LOGGER.info("Before saving customer");
		
		encryptPassword(customer);
		
		Customer savedCustomer = aquaAdminService.saveCustomer(customer);
		
		LOGGER.info("Saved following customer:: " + savedCustomer.getCustomerId());
		
		
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
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
	@GetMapping(value = "/customer/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Boolean.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public boolean isAquaUserExists(
			@Valid @Size(min= 5, max=20, message = "aqua username is inValid") @PathVariable(value = "userName", required = true) String userName,
			@Valid @Size(min= 5, max=12, message = "aqua user password is inValid") @PathVariable(value = "password", required = true) String password) {

		boolean aquaUserExists = aquaAdminService.isAquaCustomerExists(userName, password);
		return aquaUserExists;
	}



	/**
	 * @param customer
	 */
	private void encryptPassword(Customer customer) {

		if (customer.getAquaLogin() != null && customer.getAquaLogin().getUserPassword() != null
				&& customer.getAquaLogin().getUserPassword().length() > 0) {
			String userpwd = customer.getAquaLogin().getUserPassword();
			String encryptedUserPassword = bCryptPasswordEncoder.encode(userpwd);
			customer.getAquaLogin().setUserPassword(encryptedUserPassword);
		}

	}
}
