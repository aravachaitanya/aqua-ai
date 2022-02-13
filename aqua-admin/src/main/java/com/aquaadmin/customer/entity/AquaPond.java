package com.aquaadmin.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="user_ponds")
public class AquaPond implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pond_id")
	private Long pondId;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="pond_number")
	private String pondNumber;
	
	@Column(name="pond_size")
	private double pondSize;
	
	@Column(name="unit_of_measure")
	private String unitOfMeasure;
	
	@Column(name="feeding_type")
	private String feedingType;
	
	@Column(name="status")
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonIgnore
	private AquaLocation aquaLocation;

}
