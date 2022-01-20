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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="customer_id")
	private Long customerId;

	@Column(name="customer_name")
	private String fullName;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="phone_number")
	private Long PhoneNumber;
	
	@Column(name="customer_type")
	private String customerType;
	
	@Column(name="active_user_count")
	private int activeLoginCount;
	
	@Column(name="total_ponds")
	private int totalPonds;
	
	@Column(name="start_date")
	private Date startDate; 
	
	@Column(name="location_numbers")
	private int locationNumbers;
}
