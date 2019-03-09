package com.mauersu.exception;

public class MethodNotSupportException extends RuntimeException {
	
	private static final long serialVersionUID = 2636512073680952416L;

	public MethodNotSupportException(String errormsg) {
		super(errormsg);
	}
}
