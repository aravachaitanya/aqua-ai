/**
 * 
 */
package com.aquaadmin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquaadmin.customer.model.Customer;

/**
 * @author chaitanyaarava
 *
 */
@Repository
public interface AquaAdminRepo extends JpaRepository<Customer, Long> {

}
