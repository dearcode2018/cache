package com.mauersu.exception;


public class RedisInitException extends RuntimeException {

	private static final long serialVersionUID = 3014288907688601158L;

	public RedisInitException(Exception e) {
		super(e);
	}

	public RedisInitException(String msg, Throwable e1) {
		super(msg, e1);
	}
	
}
