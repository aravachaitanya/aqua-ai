/**
 * 
 */
package com.aquaadmin.customer.model;

import java.math.BigDecimal;
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
@Table(name="user_location")
public class AquaLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="location_id")
	private Long locationId;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="place")
	private String place;
	
	@Column(name="latitude")
	private BigDecimal latitude;
	
	@Column(name="longitude")
	private BigDecimal longitude;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="total_ponds")
	private int totalPonds;
	
	@Column(name="status")
	private String status;
}
