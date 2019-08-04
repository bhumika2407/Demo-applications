package com.bm.company.demoapp.exceptions;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -415388656846463256L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
