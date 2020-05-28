/**
  * @filename RedisProperties.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @type RedisProperties
 * @description 
 * @author qianye.zheng
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cache.redis")
public class RedisProperties
{

	/* IP地址 */
	private String host;
	
	/* 端口 */
	private int port;
	
	private URI uri;
	
	/*  密码 */
	private String password;
	
	/* 库索引 */
	private int database;
	
	/* 连接超时时间，单位: 毫秒 */
	private int connectTimeoutMillisec;
	
	/* 读超时时间，单位: 毫秒 */
	private int readTimeoutMillisec;
	
	/* 池化配置 */
	private Pool pool;
	
	/**
	 * 
	 * @type Pool
	 * @description 连接池
	 * @author qianye.zheng
	 */
	@Data
	public static final class Pool
	{
		/** 连接池配置(Jedis) */
		/* 是否后进先出(last in first out) */
		private boolean lifo;
		
		/* 是否采用公平原则 */
		private boolean fairness;
		
		/* 最小空闲数 */
		private int minIdle;
		
		/* 最大空闲数 */
		private int maxIdle;
		
		/* 最大连接数，单位: 毫秒 */
		private int maxTotal;
		
		/* 创建时是否测试可用 */
		private boolean testOnCreate;
		
		/* 最大等待时间，单位: 毫秒 */
		private long maxWaitMillis;
		
		/* 租借时是否测试连接可用 */
		private boolean testOnBorrow;
		
		/* 归还时是否测试连接可用 */
		private boolean testOnReturn;
		
		/* 空闲时是否测试连接可用 */
		private boolean testWhileIdle;
		
		/* 是否启用JMX */
		private boolean jmxEnabled;
		
		/* 每次运行驱逐对象的测试次数 */
		private int numTestsPerEvictionRun;
		
		/* 驱逐策略的类全路径 */
		//private String evictionPolicyClassName;
		
		/* 可被驱逐之前的最小空闲时间，单位: 毫秒. 默认半小时 -1表示不限时间 */
		private long minEvictableIdleTimeMillis;
		
		/* 软可被驱逐之前的最小空闲时间，单位: 毫秒. 默认半小时  -1表示不限时间 */
		private long softMinEvictableIdleTimeMillis;
		
		/* 驱逐运行的时间间隔， 单位: 毫秒.   -1表示不限时间 */
		private long timeBetweenEvictionRunsMillis;
		
		/* 连接池关闭后，等待驱逐者关闭的超时时间， 单位: 毫秒.   -1表示不限时间 */
		private long evictorShutdownTimeoutMillis;
		
	}
}
