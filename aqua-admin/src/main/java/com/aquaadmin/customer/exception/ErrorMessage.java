package com.aquaadmin.customer.exception;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chaitanyaarava
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private OffsetDateTime timestamp;
	
	private String message;
	
}
