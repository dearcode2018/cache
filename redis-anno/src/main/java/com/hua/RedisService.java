/**
  * @filename RedisService.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

 /**
 * @type RedisService
 * @description  
 * @author qianye.zheng
 */
@Service
public class RedisService
{

	@Resource
	protected StringRedisTemplate redisTemplate;
	
	/**
	 * 
	 * @description 缓存1天，第二天凌晨0点过期
	 * @param key
	 * @author qianye.zheng
	 */
	public void cacheOneDay(final String key)
	{
		final Calendar calendar = Calendar.getInstance();
		// 明天
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// 设置过期时间
		redisTemplate.expireAt(key, calendar.getTime());
	}
}
