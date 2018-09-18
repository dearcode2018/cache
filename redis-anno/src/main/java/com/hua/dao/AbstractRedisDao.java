/**
  * @filename AbstractRedisDao.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.dao;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.hua.dao.impl.CoreDaoImpl;

 /**
 * @type AbstractRedisDao
 * @description 
 * @author qianye.zheng
 */
public abstract class AbstractRedisDao<K, V> {

	/* apache commons log */
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	protected RedisTemplate<K, V> redisTemplate;

	/**
	 * @param redisTemplate the redisTemplate to set
	 */
	public final void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	protected RedisSerializer<String> getRedisSerializer()
	{
		return redisTemplate.getStringSerializer();
	}
	
}
