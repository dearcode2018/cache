/**
  * @filename RedisConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @type RedisConfiguration
 * @description
 * @author qianye.zheng
 */
@Configuration
public class RedisConfiguration
{

	@Resource
	private RedisProperties redisProperties;
	
	/* 锁前缀 */
	@Value("${cache.lock.prefix:lock_prefix}")
	private String lockPrefix;
	
	// 设置锁统一的失效时间
	@Value("${cache.lock.expireAfterMillisec:90000}")
	private long expireAfterMillisec;
	
	/**
	 * 
	 * @description 
	 * @param redisConnectionFactory
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RedisLockRegistry registry(final RedisConnectionFactory redisConnectionFactory) {
		return new RedisLockRegistry(redisConnectionFactory, lockPrefix, expireAfterMillisec);
	}
	
	/**
	 * 
	 * @description
	 * @return
	 * @author qianye.zheng
	 */
	//@Primary
	@Bean
	public RedisConnectionFactory connectionFactory()
	{
		final RedisStandaloneConfiguration re = new RedisStandaloneConfiguration();
		re.setHostName(redisProperties.getHost());
		re.setPassword(redisProperties.getPassword());
		re.setDatabase(redisProperties.getDatabase());
		re.setPort(redisProperties.getPort());
		final JedisClientConfiguration.JedisClientConfigurationBuilder  builder = JedisClientConfiguration.builder();
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
		poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
		poolConfig.setMaxTotal(redisProperties.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWaitMillis());
		poolConfig.setTestOnCreate(true);
		builder.usePooling().poolConfig(poolConfig);
		builder.readTimeout(redisProperties.getReadTimeout()).connectTimeout(redisProperties.getConnectTimeout());
		
		return new JedisConnectionFactory(re);
	}
	
}
