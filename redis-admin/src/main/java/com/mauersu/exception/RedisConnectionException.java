package com.mauersu.exception;

public class RedisConnectionException extends RuntimeException {

	private static final long serialVersionUID = 1278432585372300539L;

	public RedisConnectionException(String errormsg) {
		super(errormsg);
	}

}
