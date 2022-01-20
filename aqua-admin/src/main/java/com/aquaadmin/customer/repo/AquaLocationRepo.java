/**
 * 
 */
package com.aquaadmin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aquaadmin.customer.model.AquaLocation;

/**
 * @author chaitanyaarava
 *
 */
public interface AquaLocationRepo extends JpaRepository<AquaLocation, Long> {

}
