/**
  * @filename EhcacheService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.CacheConfigurationBuilder;


/**
 * @type EhcacheService
 * @description 
 * @author qianye.zheng
 */
public class EhcacheService
{
	
	private static CacheManager cacheManager;
	
	private static final String CACHE_NAME = "SSO_CLIENT_CACHE";
	
	private static 	Cache<String, String> cache;
	
	static
	{
		// 初始化缓存对象
		CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder()
				.withExpiry(new CustomExpiry()).buildConfig(String.class, String.class);
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache(CACHE_NAME, cacheConfiguration).build();
		cache = cacheManager.getCache(CACHE_NAME, String.class, String.class);
	}
	
	/**
	 * 
	 * @description 放入缓存
	 * @param key 键
	 * @param value 值
	 * @author qianye.zheng
	 */
	public static final void put(final String key, final String value)
	{
		cache.put(key, value);
	}
	
	/**
	 * 
	 * @description 从缓存获取
	 * @param key 键
	 * @author qianye.zheng
	 */
	public static final String get(final String key)
	{
		return cache.get(key);
	}
	
	/**
	 * 
	 * @description 从缓存中移除
	 * @param key 键
	 * @author qianye.zheng
	 */
	public static final void remove(final String key)
	{
		cache.remove(key);
	}
	
	/**
	 * 
	 * @description 缓存中是否含存在
	 * @param key 键
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean contain(final String key)
	{
		return cache.containsKey(key);
	}
	
}
