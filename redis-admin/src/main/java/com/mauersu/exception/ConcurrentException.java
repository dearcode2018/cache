package com.mauersu.exception;

public class ConcurrentException extends RuntimeException {

	private static final long serialVersionUID = -8034809336197661191L;

	public ConcurrentException(String msg) {
		super(msg);
	}
	
}
