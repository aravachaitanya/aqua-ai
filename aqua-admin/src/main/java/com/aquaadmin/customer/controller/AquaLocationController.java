package com.aquaadmin.customer.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aquaadmin.customer.entity.AquaLocation;
import com.aquaadmin.customer.entity.AquaPond;
import com.aquaadmin.customer.exception.AquaAdminException;
import com.aquaadmin.customer.exception.ErrorMessage;
import com.aquaadmin.customer.model.AquaCustomerLocation;
import com.aquaadmin.customer.model.AquaCustomerPond;
import com.aquaadmin.customer.service.AquaLocationService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author chaitanyaarava
 *
 */
@RestController
@Validated
public class AquaLocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AquaLocationController.class);

	@Autowired
	private AquaLocationService aquaLocationService;

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws AquaAdminException
	 */
	@PostMapping("/saveLocation/{customerId}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AquaLocation.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Access Denied", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "Customer Not Found", response = ErrorMessage.class),
			@ApiResponse(code = 500, message = "Internel Server Error", response = ErrorMessage.class) })
	public ResponseEntity<AquaLocation> saveCustomer(@PathVariable("customerId") @Valid @Min(value = 1) Long customerId,
			@Valid @RequestBody AquaCustomerLocation aquaCustomerLocation) throws AquaAdminException {

		LOGGER.info("Before saving aquaLocation");

		AquaLocation savedAquaLocation = aquaLocationService.saveAquaLocation(mapAquaCustomerLocation(customerId, aquaCustomerLocation));

		LOGGER.info("Saved following customer:: " + savedAquaLocation.getLocationId());

		return new ResponseEntity<AquaLocation>(savedAquaLocation, HttpStatus.CREATED);
	}

	private AquaLocation mapAquaCustomerLocation(@Valid Long customerId, @Valid AquaCustomerLocation aquaCustomerLocation) {

		AquaLocation aquaLocation = new AquaLocation();

		aquaLocation.setCustomerId(customerId);
		aquaLocation.setPlace(aquaCustomerLocation.getPlace());
		aquaLocation.setLatitude(aquaCustomerLocation.getLatitude());
		aquaLocation.setLongitude(aquaCustomerLocation.getLongitude());
		aquaLocation.setStartDate(aquaCustomerLocation.getStartDate());
		aquaLocation.setStatus(aquaCustomerLocation.getStatus());
		aquaLocation.setTotalPonds(aquaCustomerLocation.getTotalPonds());

		Set<AquaPond> aquaPonds = new HashSet<>();

		for (AquaCustomerPond aquaCustomerPond : aquaCustomerLocation.getAquaCustomerPonds()) {

			AquaPond aquaPond = new AquaPond();

			aquaPond.setFeedingType(aquaCustomerPond.getFeedingType());
			aquaPond.setPondNumber(aquaCustomerPond.getPondNumber());
			aquaPond.setPondSize(aquaCustomerPond.getPondSize());
			aquaPond.setStatus(true);
			aquaPond.setUnitOfMeasure(aquaCustomerPond.getUnitOfMeasure());
			aquaPond.setCustomerId(customerId);

			aquaPonds.add(aquaPond);

		}
		aquaLocation.setAquaPonds(aquaPonds);

		return aquaLocation;
	}

}
