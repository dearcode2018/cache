/**
  * @filename SentinelProperties.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @type SentinelProperties
 * @description 
 * @author qianye.zheng
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis.sentinel")
public class SentinelProperties
{

	/* redis 主节点名称 */
	private String master;
	
	/* 哨兵端口和地址，多个用逗号隔开 */
	private Set<String> sentinelHostAndPorts;
	
	/* 主节点的密码 */
	private String password;
	
	private Integer database;
	
	/* 池化配置 */
	private Pool pool;

	/* 连接超时时间，单位: 毫秒 */
	private Long connectTimeout;
	
	/* 读超时时间，单位: 毫秒 */
	private Long readTimeout;
	
	/**
	 * 
	 * @type Pool
	 * @description 
	 * @author qianye.zheng
	 */
	@Data
	public static final class Pool
	{
		
		/** 连接池配置(Jedis) */
		/* 最小空闲数 */
		private Integer minIdle;
		
		/* 最大空闲数 */
		private Integer maxIdle;
		
		/* 最大连接数，单位: 毫秒 */
		private Integer maxTotal;
		
		/* 创建时是否测试可用 */
		private Boolean testOnCreate;
		
		/* 最大等待时间，单位: 毫秒 */
		private Long maxWaitMillis;
	}
	
}
