package com.aquaadmin.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="aqua_login")
public class AquaLogin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_status")
	private boolean userStatus;
	
	@Column(name="user_start_date")
	private Date userStartDate;
	
	@Column(name="user_end_date")
	private Date userEndDate;

	@OneToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;

}
