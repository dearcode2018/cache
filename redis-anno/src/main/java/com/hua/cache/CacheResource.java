/**
  * @filename CacheResource.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

 /**
 * @type CacheResource
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class CacheResource
{
	
	@Resource
	private RedisConnectionFactory jedisConnectionFactory;
	
	@Bean
	public RedisCacheManager cacheManager()
	{
		return RedisCacheManager.create(jedisConnectionFactory);
	}
	
}
