/**
  * @filename RedisConfig.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.bean;

 /**
 * @type RedisConfig
 * @description 
 * @author qye.zheng
 */
public final class RedisConfig {
	
	private String host = "127.0.0.1";
	
	private int port = 6379;

	private int maxActive = 20;
	
	private int maxIdle = 5;
	
	private long maxWait = 1000L;
	
	private boolean testOnBorrow = false;
	
	
	/**
	 * @return the host
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public final void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public final int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public final void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the maxActive
	 */
	public final int getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive the maxActive to set
	 */
	public final void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the maxIdle
	 */
	public final int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @param maxIdle the maxIdle to set
	 */
	public final void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the maxWait
	 */
	public final long getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait the maxWait to set
	 */
	public final void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the testOnBorrow
	 */
	public final boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	/**
	 * @param testOnBorrow the testOnBorrow to set
	 */
	public final void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	
}
