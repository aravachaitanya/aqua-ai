package com.aquaadmin.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
@Table(name="aqua_cycle")
public class AquaCycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cycleId;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="location_id")
	private Long locationId;
	
	@Column(name="pond_id")
	private Long pondId;
	
	@Column(name="density")
	private Long density;
	
	@Column(name="hatchery")
	private String hatchery;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
}
