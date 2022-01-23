package com.aquaadmin.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chaitanyaarava
 *
 */
@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_admin")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id", unique = true)
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
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private AquaLogin aquaLogin;
}
