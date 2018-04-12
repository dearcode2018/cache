/**
 * MemcachedServiceImpl.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.service.impl;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.hua.bean.MemCachedParam;
import com.hua.service.MemcachedService;
import com.hua.util.ReadProperties;

/**
 * MemcachedServiceImpl
 * 描述: 缓存 - 服务
 * @author  qye.zheng
 */
public final class MemcachedServiceImpl extends CoreServiceImpl implements
		MemcachedService
{
	private static final String FILE_PATH = "/conf/properties/cache.properties";
	
	// 读取属性配置
	private static ReadProperties props;
	
	// 缓存参数
	private static MemCachedParam param;
	
	// cache 客户端
	private static MemCachedClient cacheClient = new MemCachedClient();
	
	// 单例化
	private static MemcachedService instance;
	
	// 设置与缓存服务器的连接池
	static 
	{
		props = new ReadProperties(FILE_PATH);
		param = new MemCachedParam(props);
		
		// 获取 sock 连接池的实例对象
		final SockIOPool pool = SockIOPool.getInstance();
		
		// 设置服务器信息
		pool.setServers(param.getServers());
		pool.setWeights(param.getWeights());
		
		// 设置参数
		// 初始连接数
		pool.setInitConn(param.getInitConnect());
		// 最小连接数
		pool.setMinConn(param.getMinConnect());
		// 最大连接数
		pool.setMaxConn(param.getMaxConnect());
		
		// 最大处理时间
		pool.setMaxIdle(param.getMaxIdle());
		// 主线程休眠时间
		pool.setMaintSleep(param.getMainThreadSleep());
		
		// 是否使用nagle算法
		pool.setNagle(param.isNagle());
		
		// socket超时
		pool.setSocketConnectTO(param.getSocketTimeOut());
		// socket连接超时
		pool.setSocketConnectTO(param.getSocketConnectTimeOut());
		
		// 连接池 初始化
		pool.initialize();
		
		// 是否启用压缩 (方法已过时)
		//cacheClient.setCompressEnable(MemCachedConstant.COMPRESS_ENABLE);
		
		// 超过此设置的数据都会被压缩 (单位 : kb)  (方法已过时)
		//cacheClient.setCompressThreshold(MemCachedConstant.COMPRESS_THRESHOLD);
	}
	
	/**
	 * 
	 * 描述: 获取单一实例
	 * @author  qye.zheng
	 * @return
	 */
	public static MemcachedService getInstance()
	{
		if (null == instance)
		{
			instance = new MemcachedServiceImpl();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean set(final String key, final Object value) {
		
		return cacheClient.set(key, value);
	}
	
	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiryDateTime 过期时间
	 * @return
	 */
	@Override
	public boolean set(final String key, final Object value, final Date expiryDateTime) {
		
		return cacheClient.set(key, value);
	}
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean add(final String key, final Object value) {

		return cacheClient.add(key, value);
	}
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiryDateTime 过期时间
	 * @return
	 */
	@Override
	public boolean add(final String key, final Object value, final Date expiryDateTime) {
		
		return cacheClient.add(key, value, expiryDateTime);
	}
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean replace(final String key, final Object value) {
		
		return cacheClient.replace(key, value);
	}
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiryDateTime 过期时间
	 * @return
	 */
	@Override
	public boolean replace(final String key, final Object value, final Date expiryDateTime) {
		
		return cacheClient.replace(key, value, expiryDateTime);
	}
	
	/**
	 * 
	 * 描述: 根据key从缓存中移除对象
	 * @author qye.zheng
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Object delete(final String key) {
		
		return cacheClient.delete(key);
	}
	
	/**
	 * 
	 * 描述: 根据key从缓存中获取对象
	 * @author qye.zheng
	 * @param key
	 * @return
	 */
	@Override
	public Object get(final String key) {
		
		return cacheClient.get(key);
	}
	
	
}
