/**
  * @filename RedisCacheImpl.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import java.io.Serializable;
import java.util.Date;

import redis.clients.jedis.Jedis;

import com.hua.constant.CacheConstant;
import com.hua.util.RedisClient;
import com.hua.util.SerializationUtil;

 /**
 * @type RedisCacheImpl
 * @description 
 * @author qye.zheng
 */
public final class RedisCacheImpl implements ICache {

	/* 非切片客户端连接 */
	private final Jedis jedis = new RedisClient().getJedis();
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public RedisCacheImpl() {
		jedis.set(CacheConstant.TEST_KEY, "testValue");
	}
	
	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public final boolean isLocal() {
		return false;
	}

	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public final boolean isAvailable() {
		String value = jedis.get(CacheConstant.TEST_KEY);
		if (null != value)
		{
			return true;
		}
		
		return false;
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final boolean keyExists(final String key) {
		return jedis.exists(key);
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	public final boolean put(final String key, final Object value) {
		/**
		 * key 和 value 都要序列化成字节数组，然后再存储
		 * key: 字符串 getBytes()
		 * value: 对象 - SerializationUtil.serialize()
		 */
		boolean flag = false;
		if (null == value)
		{
			return flag;
		}
		try {
			jedis.set(key.getBytes(), SerializationUtil.serialize((Serializable) value));
			
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @param expireDt
	 * @return
	 * @author qye.zheng
	 */
	public final boolean put(final String key, final Object value, final Date expireDt) {
		/**
		 * key 和 value 都要序列化成字节数组，然后再存储
		 * key: 字符串 getBytes()
		 * value: 对象 - SerializationUtil.serialize()
		 */
		boolean flag = false;
		if (null == value)
		{
			return flag;
		}
		try {
			// 当前日期时间
			final Date now = new Date();
			/**
			 * 当前时间时间 减去 失效日期时间，计算出存活的秒数
			 */
			final int seconds = (int) (expireDt.getTime() / 1000 - now.getTime() / 1000);
			jedis.setex(key.getBytes(), seconds, SerializationUtil.serialize((Serializable) value));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final Object get(final String key) {
		final byte[] value = jedis.get(key.getBytes());
		if (null == value)
		{
			return null;
		}
		Object result = SerializationUtil.deserialize(value);
		
		return result;
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final boolean delete(final String key) {
		
		boolean flag = false;
		try {
			// 转成 秒
			jedis.del(key.getBytes());
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}

}
