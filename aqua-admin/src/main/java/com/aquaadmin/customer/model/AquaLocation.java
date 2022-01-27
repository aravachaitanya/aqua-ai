package com.aquaadmin.customer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="user_location")
public class AquaLocation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Set<AquaPonds> aquaPonds;
}
