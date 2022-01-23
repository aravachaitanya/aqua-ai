package com.aquaadmin.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquaadmin.customer.model.AquaLocation;
import com.aquaadmin.customer.repo.AquaLocationRepo;

/**
 * @author chaitanyaarava
 *
 */
@Service
public class AquaLocationServiceImpl implements AquaLocationService {

	@Autowired
	private AquaLocationRepo aquaLocationRepo;
	
	@Override
	public AquaLocation saveAquaLocation(AquaLocation aquaLocation) {
		
		AquaLocation savedAquaLocation = aquaLocationRepo.save(aquaLocation);
		return savedAquaLocation;
	}

}