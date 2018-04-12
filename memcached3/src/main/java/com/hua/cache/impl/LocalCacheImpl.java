/**
  * @filename LocalCacheImpl.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.hua.bean.CacheObject;
import com.hua.cache.ICache;

 /**
 * @type LocalCacheImpl
 * @description 本地缓存
 * @author qye.zheng
 */
public final class LocalCacheImpl extends BaseCache implements ICache {

	private static final long serialVersionUID = 1L;
	
	/* 存储数据 map */
	private static Map<String, Object> cacheDataMap = Collections.synchronizedMap(new HashMap<String, Object>());
	
	/**
	 * 自动扫描: 实现在指定周期 清除缓存中失效的数据.
	 */
	/* 自动扫描的频率 (单位: 秒) */
	private int autoScanSeconds = 3;
	
	/**
	 * @param autoScanSeconds the autoScanSeconds to set
	 */
	public final void setAutoScanSeconds(int autoScanSeconds) {
		this.autoScanSeconds = autoScanSeconds;
	}

	/**
	 * @description 初始化
	 * @author qye.zheng
	 */
	@Override
	public void init() {
		super.init();
		
		// 定时器
		final Timer taskTimer = new Timer(true);
		// 延迟时间
		final long delay = 3 * 1000;
		// 以固定周期执行定时任务
		taskTimer.scheduleAtFixedRate(new ClearExpireDataTask(), delay, autoScanSeconds * 1000);
		log.info("Local cache object has been created.");
	}
	
	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean isLocal() {
		return true;
	}

	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean isAvailable() {
		return true;
	}

	/**
	 * @description 判断 键 是否存在
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean keyExists(String key) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		
		return cacheDataMap.containsKey(key);
	}

	/**
	 * @description 存储键值对 (无失效日期时间)
	 * @param key
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean put(String key, final Object value) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		synchronized (cacheDataMap)
		{
			cacheDataMap.put(key, value);
		}
		
		return true;
	}

	/**
	 * @description 存储键值对
	 * @param key
	 * @param value
	 * @param expireDt 失效日期时间
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean put(String key, final Object value, final Date expireDt) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		// 若是 同步 Map，可以不用主动加synchronized
		synchronized (cacheDataMap)
		{
			cacheDataMap.put( key, new CacheObject(key, value, expireDt) );
		}
		
		return true;
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean delete(String key) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		synchronized (cacheDataMap)
		{
			cacheDataMap.remove(key);
		}
		
		return false;
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public Object get(String key) {
		/**
		 * 获取的时候，判断是否过期，过期但是还在缓存区的，则不再取出，并且从缓存中移除该项
		 */
		if (keyIsEmpty(key))
		{
			return null;
		}
		key = super.getFirstKey(key);
		Object obj = cacheDataMap.get(key);
		if (obj instanceof CacheObject)
		{
			CacheObject co = (CacheObject) obj;
			if (co.isAvailable())
			{
				obj = co.getObject();
			} else
			{
				// 置空
				obj = null;
				// 从缓存中移除该项
				cacheDataMap.remove(key);
			}
		}
		
		return obj;
	}

	/**
	 * 
	 * @type ClearExpireDataTask
	 * @description 清除失效数据 - 任务调度
	 * @author qye.zheng
	 */
	private final class ClearExpireDataTask extends TimerTask
	{
		/**
		 * @description 
		 * @author qye.zheng
		 */
		public void run() {
			log.info("Clear the expired data from local cache...");
			/**
			 * 实现思路
			 * 1) 直接遍历 cacheDataMap 对象，获取CacheObject对象，若改对象不可用(失效)，
			 * 则直接 remove，这种方式在访问cacheDataMap的时候回加锁，这样会严重降低性能，
			 * 如果缓存区没有大量失效的对象，则每次扫描都会锁住该对象，严重降低效率.
			 * 
			 * 2) 遍历 cacheDataMap，取出 CacheObject 对象，判断是否不可用，不可用则将其key存放到
			 * 一个Set<String> 中，访问cacheDataMap(若是SyncrhonizedMap对象则任何操作都会加锁，包括读取)
			 * 接下来，若removeKeySet不为空，则remove的时候，加锁.
			 * 
			 * 提示: 其实SynchronizedMap.get 方法也是有加锁的，因此，访问cacheDataMap都会进行加锁，因此，若使用
			 * SynchronizedMap 则使用方式1，若不是同步Map，则使用方式2来主动加锁.
			 * 
			 * 这里虽然使用了同步Map，但依然采用方式2，因为方式2相对方式1复杂.
			 */
			Set<String> keySet = cacheDataMap.keySet();
			Set<String> removeKeySet = new HashSet<String>();
			Object obj = null;
			CacheObject co = null;
			// 遍历取出不可用项的键
			for (String key : keySet)
			{
				obj = cacheDataMap.get(key);
				if (obj instanceof CacheObject)
				{
					co = (CacheObject) obj;
					if (!co.isAvailable())
					{
						removeKeySet.add(key);
					}
				}
			}
			// 移除不可用项
			if (removeKeySet.size() > 0)
			{
				for (String key : removeKeySet)
				{
					synchronized (cacheDataMap)
					{
						cacheDataMap.remove(key);
					}
					log.info("Remove object[" + key + "] from local cache");
				}
			}
		}
	}
	
}
