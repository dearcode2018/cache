/**
  * @filename CacheManager.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import java.util.Date;

 /**
 * @type CacheManager
 * @description 
 * @author qye.zheng
 */
public final class CacheManager {

	private ICache cache;
	
	private static CacheManager instance = null;
	
	private CacheManager()
	{
		cache = new RedisCacheImpl();
		//cache = CoreApplicationContext.getApplicationContext().getBean(ICache.class);
	}

	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public static final synchronized CacheManager getInstance()
	{
		if (instance == null)
		{
			instance = new CacheManager();
		}
		return instance;
	}

	/**
	 * 
	 * @description 
	 * @param value
	 * @author qye.zheng
	 */
	public final void setCache(ICache value)
	{
		cache = value;
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final boolean keyExists(String key)
	{
		return cache.keyExists(key);
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	public final boolean put(String key, Object value)
	{
		return cache.put(key, value);
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @param value
	 * @param expireDt
	 * @return
	 * @author qye.zheng
	 */
	public final boolean put(String key, Object value, Date expireDt)
	{
		return cache.put(key, value, expireDt);
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final Object get(String key)
	{
		return cache.get(key);
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public final boolean delete(String key)
	{
		return cache.delete(key);
	}
}
