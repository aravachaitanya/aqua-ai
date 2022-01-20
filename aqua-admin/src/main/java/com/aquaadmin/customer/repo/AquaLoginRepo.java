/**
 * 
 */
package com.aquaadmin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquaadmin.customer.model.AquaLogin;

/**
 * @author chaitanyaarava
 *
 */
@Repository
public interface AquaLoginRepo extends JpaRepository<AquaLogin, Long> {

}
