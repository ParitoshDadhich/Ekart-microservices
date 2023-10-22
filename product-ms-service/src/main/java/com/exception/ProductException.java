package com.exception;

import org.jboss.logging.Logger;

public class ProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ProductException.class);
	
	public ProductException(String exceptionMessage) {
		super(exceptionMessage);
		logger.error(exceptionMessage);
	}
	
}
