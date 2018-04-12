package com.hua.cache;

import java.util.Date;

public class CacheManager
{
	private static CacheManager instance = null;
	private static ICache cache;

	private CacheManager()
	{
		//cache = CoreApplicationContext.getApplicationContext().getBean(ICache.class);
	}

	public static synchronized CacheManager getInstance()
	{
		if (instance == null)
		{
			instance = new CacheManager();
		}
		return instance;
	}

	public void setCache(ICache value)
	{
		cache = value;
	}

	public boolean keyExists(String key)
	{
		return cache.keyExists(key);
	}

	public boolean put(String key, Object value)
	{
		return cache.put(key, value);
	}

	public boolean put(String key, Object value, Date expireDt)
	{
		return cache.put(key, value, expireDt);
	}

	public Object get(String key)
	{
		return cache.get(key);
	}

	public boolean delete(String key)
	{
		return cache.delete(key);
	}
}
