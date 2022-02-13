package com.aquaadmin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquaadmin.customer.entity.AquaLocation;

/**
 * @author chaitanyaarava
 *
 */
@Repository
public interface AquaLocationRepo extends JpaRepository<AquaLocation, Long> {

}
