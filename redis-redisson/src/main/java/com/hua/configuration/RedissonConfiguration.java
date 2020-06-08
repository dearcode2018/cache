/**
  * @filename RedissonConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import javax.annotation.Resource;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @type RedissonConfiguration
 * @description
 * @author qianye.zheng
 */
@Configuration
public class RedissonConfiguration
{

	@Resource
	private RedisProperties redisProperties;

	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RedissonClient client() {
		final Config config = new Config();
		config.useSingleServer().setAddress(redisProperties.getUri().toString())
		.setPassword(redisProperties.getPassword())
		.setConnectionPoolSize(redisProperties.getPool().getMaxTotal())
		.setConnectTimeout(redisProperties.getConnectTimeoutMillisec())
		.setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle())
		.setDatabase(redisProperties.getDatabase());
		
		return Redisson.create(config);
	}
	
}
