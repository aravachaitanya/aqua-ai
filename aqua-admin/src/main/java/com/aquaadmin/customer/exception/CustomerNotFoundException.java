package com.aquaadmin.customer.exception;

/**
 * @author chaitanyaarava
 *
 */
public class CustomerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 54333256241853067L;

	public CustomerNotFoundException(String message) {
		this(message, null);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super( message, cause);
	}
}
