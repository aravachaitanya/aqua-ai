/**
 * 
 */
package com.aquaadmin.customer.exception;

import org.springframework.http.HttpStatus;

/**
 * @author chaitanyaarava
 *
 */
public class AquaAdminException extends RuntimeException {

	private final HttpStatus httpStatus;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2944182626913407272L;

	public AquaAdminException() {
		super();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	
	public AquaAdminException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public AquaAdminException(String message, Throwable cause) {
		super(message, cause);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public AquaAdminException(Throwable cause, HttpStatus httpStatus) {
		super(cause);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getStatusCode() {
		return httpStatus;
	}
}
