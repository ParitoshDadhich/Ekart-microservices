package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProductException.class);
	
	public ProductException(String exceptionMessage) {
		super(exceptionMessage);
		logger.error(exceptionMessage);
	}
	
}
