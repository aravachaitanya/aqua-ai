/**
 * 
 */
package com.aquaadmin.customer.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aquaadmin.customer.exception.AquaAdminException;
import com.aquaadmin.customer.exception.ErrorMessage;
import com.aquaadmin.customer.model.AquaLogin;
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

		Optional<Customer> customer = aquaAdminService.getCustomerById(customerId);
		if (!customer.isPresent()) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	} 
	
	/**
	 * This method is to save Aqua login details into aqua_login table.
	 * 
	 * @param AquaLogin
	 * @return
	 * @throws AquaAdminException
	 */
	@PostMapping("/saveAquaLoginDetails")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = AquaLogin.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class)
	})
	public ResponseEntity<AquaLogin> saveAquaLoginDetails(@Valid @RequestBody AquaLogin aquaLogin) throws AquaAdminException {
		
		LOGGER.info("Before saving AquaLogin");
		
		AquaLogin savedAquaLoginDetails = aquaAdminService.saveAquaLoginDetails(aquaLogin);
		
		LOGGER.info("Saved following credentials:: " + savedAquaLoginDetails.getUserId());
		
		
		return new ResponseEntity<AquaLogin>(savedAquaLoginDetails, HttpStatus.CREATED);
	}
}
