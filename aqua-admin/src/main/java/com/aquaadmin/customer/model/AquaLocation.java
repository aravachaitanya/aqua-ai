/**
 * 
 */
package com.aquaadmin.customer.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Column(name="location_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	
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
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private Customer customer;
}
