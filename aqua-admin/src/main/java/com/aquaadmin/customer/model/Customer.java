/**
 * 
 */
package com.aquaadmin.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chaitanyaarava
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_admin")
public class Customer {
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;

	@Column(name="customer_name")
	@NotBlank(message = "customer name is not valid")
	private String fullName;
	
	@Column(name="email_id")
	@NotBlank(message = "customer emailId is not valid")
	private String emailId;
	
	@Column(name="location_number")
	private int locationNumber;
	
	@Column(name="customer_type")
	@NotBlank(message = "customer type is not valid")
	private String customerType;
	
	@Column(name="active_user_count")
	private int activeLoginCount;
	
	@Column(name="total_ponds")
	private int totalPonds;
	
	@Column(name="start_date")
	private Date startDate; 
}
