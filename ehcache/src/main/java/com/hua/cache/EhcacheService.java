/**
  * @filename EhcacheService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

/**
 * @type EhcacheService
 * @description 
 * @author qianye.zheng
 */
public class EhcacheService
{
	
	private static PersistentCacheManager persistentCacheManager;
	
	private static final String CACHE_NAME = "SSO_CLIENT_CACHE";
	
	private static 	Cache<String, String> cache;
	
	static
	{
		// 初始化缓存对象
		 persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				 .with(CacheManagerBuilder.persistence(System.getProperty("java.io.tmpdir") + "ehcache"))
						 .withCache(CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
			                ResourcePoolsBuilder.newResourcePoolsBuilder()
			                    .heap(50, EntryUnit.ENTRIES)  // 堆
			                    .offheap(50, MemoryUnit.MB)    // 堆外
			                    .disk(20, MemoryUnit.GB)      // 磁盘
			                )
				// 是否需要初始化
				).build(true);
		 cache = persistentCacheManager.getCache(CACHE_NAME, String.class, String.class);
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
