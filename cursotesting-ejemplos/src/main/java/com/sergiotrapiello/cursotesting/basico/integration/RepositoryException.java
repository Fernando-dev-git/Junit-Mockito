package com.sergiotrapiello.cursotesting.basico.integration;

/**
 * Exception generic for error produced in the cap DAO
 * 
 * @author Sergio
 *
 */
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
