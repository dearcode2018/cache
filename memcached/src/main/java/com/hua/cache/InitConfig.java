/**
 * 描述: 
 * InitConfig.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.cache;

import com.hua.util.ReadProperties;

/**
 * 描述: 初始化配置
 * 
 * @author qye.zheng
 * InitConfig
 */
public final class InitConfig {
	
	private static String[] servers;
	
	private static Integer[] weights;
	
	private static int initConnect;
	
	private static int minConnect;
	
	private static int maxConnect;
	
	private static long maxIdle;
	
	private static boolean isNagle;
	
	private static int socketTimeOut;
	
	private static int socketConnectTimeOut;
	
	private static boolean compressEnable;
	
	private static long compressThreshold;
	
	private static ReadProperties props = new ReadProperties("conf/memcached.properties");

	/**
	 * @return the servers
	 */
	public static String[] getServers() {
		servers = props.getProperty("servers").split(",");
		
		return servers;
	}

	/**
	 * @return the weights
	 */
	public static Integer[] getWeights() {
		final String[] str = props.getProperty("weights").split(",");
		
		
		return weights;
	}

	/**
	 * @return the initConnect
	 */
	public static int getInitConnect() {
		return initConnect;
	}

	/**
	 * @return the minConnect
	 */
	public static int getMinConnect() {
		return minConnect;
	}

	/**
	 * @return the maxConnect
	 */
	public static int getMaxConnect() {
		return maxConnect;
	}

	/**
	 * @return the maxIdle
	 */
	public static long getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @return the isNagle
	 */
	public static boolean isNagle() {
		return isNagle;
	}

	/**
	 * @return the socketTimeOut
	 */
	public static int getSocketTimeOut() {
		return socketTimeOut;
	}

	/**
	 * @return the socketConnectTimeOut
	 */
	public static int getSocketConnectTimeOut() {
		return socketConnectTimeOut;
	}

	/**
	 * @return the compressEnable
	 */
	public static boolean isCompressEnable() {
		return compressEnable;
	}

	/**
	 * @return the compressThreshold
	 */
	public static long getCompressThreshold() {
		return compressThreshold;
	}

	/**
	 * @return the props
	 */
	public static ReadProperties getProps() {
		return props;
	}
	
	
}
