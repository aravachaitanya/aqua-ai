package com.aquaadmin.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aquaadmin.customer.entity.AquaLogin;

/**
 * @author chaitanyaarava
 *
 */
@Repository
public interface AquaLoginRepo extends JpaRepository<AquaLogin, Long> {

	AquaLogin findByUserName(String username);
	
	@Query(value = "SELECT a from AquaLogin a where a.userName=?1")
	AquaLogin findByUserNameAndUserPassword(String userName, String password);

}
