/**
  * @filename CacheManager.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import java.util.Date;

import com.hua.common.CoreApplicationContext;
import com.hua.util.SpringCacheUtil;

 /**
 * @type CacheManager
 * @description 缓存管理器
 * @author qye.zheng
 */
public final class CacheManager {
	
	private static ICache cache;
	
	/**
	 * 
	 * @description 构造方法
	 * @author qye.zheng
	 */
	private CacheManager()
	{
		cache = SpringCacheUtil.getBeanFactoryOfXml().getBean(ICache.class);
	}
	
	/**
	 * @param cache the cache to set
	 */
	public static final void setCache(ICache cache) {
		CacheManager.cache = cache;
	}

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public boolean keyExists(final String key)
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
	public boolean put(final String key, final Object value)
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
	public boolean put(final String key, final Object value, final Date expireDt)
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
	public boolean delete(final String key)
	{
		return cache.delete(key);
	}
	
	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public Object get(final String key)
	{
		return cache.get(key);
	}
	
	/**
	 * 
	 * @type InnerCacheManager
	 * @description 
	 * @author qye.zheng
	 */
	private static final class InnerCacheManager
	{
		private static CacheManager instance = new CacheManager();
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public static final CacheManager getInstance()
	{
		return InnerCacheManager.instance;
	}
	
}
