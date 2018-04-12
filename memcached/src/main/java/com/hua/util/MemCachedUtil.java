/**
 * 描述: 
 * MemcachedUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;

/**
 * 描述: 缓存 - 工具类
 * @author qye.zheng
 * MemcachedUtil
 */
public final class MemCachedUtil {
	
	/* memcached 客户端 */
	private static MemCachedClient client = new MemCachedClient();
	
	/* 单一实例 */
	private static MemCachedUtil instance = null;
	
	// 设置与缓存服务器的连接池
	static {
		
	}
	
	/** 无参构造方法 */
	private MemCachedUtil() {}
	
	/**
	 * 
	 * 描述: 获取单例对象
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static MemCachedUtil getInstance() {
		if (null == instance) {
			instance = new MemCachedUtil();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, final Object value) {
		
		return client.set(key, value);
	}
	
	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean set(final String key, final Object value, final Date expiry) {
		
		return client.set(key, value);
	}
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(final String key, final Object value) {

		return client.add(key, value);
	}
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean add(final String key, final Object value, final Date expiry) {
		
		return client.add(key, value, expiry);
	}
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(final String key, final Object value) {
		
		return client.replace(key, value);
	}
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean replace(final String key, final Object value, final Date expiry) {
		
		return client.replace(key, value, expiry);
	}
	
	/**
	 * 
	 * 描述: 根据key从缓存中移除对象
	 * @author qye.zheng
	 * 
	 * @param key
	 * @return
	 */
	public Object delete(final String key) {
		
		return client.delete(key);
	}
	
	/**
	 * 
	 * 描述: 根据key从缓存中获取对象
	 * @author qye.zheng
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		
		return client.get(key);
	}
	
}
