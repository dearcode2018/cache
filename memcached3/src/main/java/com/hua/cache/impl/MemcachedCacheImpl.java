/**
  * @filename MemcachedCacheImpl.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache.impl;

import java.io.Serializable;
import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.hua.bean.MemcachedConfig;
import com.hua.cache.ICache;
import com.hua.util.SerializationUtil;

 /**
 * @type MemcachedCacheImpl
 * @description 
 * @author qye.zheng
 */
public class MemcachedCacheImpl extends BaseCache implements ICache {

	/**
	 * 存储: 键可以进行特定的编码，例如 md5编码;
	 * 值序列化之后再存放，取出来时执行反序列化.
	 */
	
	private static final long serialVersionUID = 1L;
	
	private MemcachedConfig memcachedConfig;
	
	private SockIOPool sockIOPool;
	
	private MemCachedClient memCachedClient;
	
	/**
	 * 测试 key 和 value 可以用来检测 缓存管理器是否可用
	 */
	
	/* 测试 key */
	private final String TEST_KEY = "TEST_CACHE";
	
	/* 测试 value */
	private final String TEST_VALUE = "V";

	/**
	 * @description 初始化
	 * @author qye.zheng
	 */
	@Override
	public void init() {
		super.init();
		
		sockIOPool = SockIOPool.getInstance();
		sockIOPool.setServers(memcachedConfig.getServers());
		sockIOPool.setWeights(memcachedConfig.getWeights());
		sockIOPool.setFailback(memcachedConfig.getFailback());
		sockIOPool.setFailover(memcachedConfig.getFailover());
		sockIOPool.setNagle(memcachedConfig.getNagle());
		sockIOPool.setAliveCheck(memcachedConfig.getAliveCheck());
		sockIOPool.setInitConn(memcachedConfig.getInitConn());
		sockIOPool.setMinConn(memcachedConfig.getMinConn());
		sockIOPool.setMaxConn(memcachedConfig.getMaxConn());
		sockIOPool.setMaxIdle(memcachedConfig.getMaxIdle());
		sockIOPool.setMaintSleep(memcachedConfig.getMaintSleep());
		sockIOPool.setSocketTO(memcachedConfig.getSocketTO());
		sockIOPool.initialize();
		memCachedClient = new MemCachedClient();
		
		// 可以把一些系统键值放入缓存区
		memCachedClient.add(TEST_KEY, TEST_VALUE);
		
		log.info("Memcached cache has been created and inited,the config as[" + memcachedConfig + "]");
	}
	
	/**
	 * @description 是否是本地缓存
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean isLocal() {
		return false;
	}

	/**
	 * @description 缓存管理器是否可用
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean isAvailable() {
		// 通过判断 测试键值对 来检验缓存管理器是否可用
		
		return null != memCachedClient.get(TEST_KEY);
	}

	/**
	 * @description 键 是否存在
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
		
		return memCachedClient.keyExists(key);
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean put(String key, Object value) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		try
		{
			// 序列化 对象值
			value = SerializationUtil.serialize((Serializable) value);
		} catch (Exception e)
		{
			// 序列化失败
			log.info("put =====> ", e);
			
			return false;
		}
		boolean flag = false;
		if (memCachedClient.keyExists(key))
		{
			// 存在 执行更新
			flag = memCachedClient.replace(key, value);
		} else
		{
			// 不存在，新增
			flag = memCachedClient.add(key, value);
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
	@Override
	public boolean put(String key, Object value, Date expireDt) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		try
		{
			// 序列化 对象值
			value = SerializationUtil.serialize((Serializable) value);
		} catch (Exception e)
		{
			// 序列化失败
			log.info("put =====> ", e);
			
			return false;
		}
		boolean flag = false;
		if (memCachedClient.keyExists(key))
		{
			// 存在 执行更新
			flag = memCachedClient.replace(key, value, expireDt);
		} else
		{
			// 不存在，新增
			flag = memCachedClient.add(key, value, expireDt);
		}
		
		return flag;
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
		
		return memCachedClient.delete(key);
	}

	/**
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public Object get(String key) {
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		Object value = null;
		try
		{
			value = memCachedClient.get(key);
			if (null != value)
			{
				// 反序列化
				value = SerializationUtil.deserialize((byte[]) value);
			}
		} catch (Exception e)
		{
			// 反序列化 失败
			log.error("get =====> ", e);
		}
		
		return value;
	}

	/**
	 * @param memcachedConfig the memcachedConfig to set
	 */
	public final void setMemcachedConfig(MemcachedConfig memcachedConfig) {
		this.memcachedConfig = memcachedConfig;
	}

}
