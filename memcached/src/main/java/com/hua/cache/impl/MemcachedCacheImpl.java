package com.hua.cache.impl;

import java.util.Date;

import com.hua.cache.ICache;

public class MemcachedCacheImpl extends BaseCache implements ICache
{

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	@Override
	public boolean isLocal()
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	@Override
	public boolean isAvailable()
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param key
	 * @return
	 */
	@Override
	public boolean keyExists(String key)
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean put(String key, Object value)
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param key
	 * @param value
	 * @param expireDt
	 * @return
	 */
	@Override
	public boolean put(String key, Object value, Date expireDt)
	{
		return false;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param key
	 * @return
	 */
	@Override
	public Object get(String key)
	{
		return null;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param key
	 * @return
	 */
	@Override
	public boolean delete(String key)
	{
		return false;
	}
	
	
}
