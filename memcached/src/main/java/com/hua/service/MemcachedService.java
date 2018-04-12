/**
 * MemcachedService.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.service;

import java.util.Date;

/**
 * MemcachedService
 * 描述: 缓存 - 服务
 * @author  qye.zheng
 */
public interface MemcachedService extends CoreService
{

	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, final Object value);
	
	/**
	 * 
	 * 描述: 设置到缓存中
	 * 缓存中没有则添加(add)，有则替换(replace)
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean set(final String key, final Object value, final Date expiry);
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(final String key, final Object value);
	
	/**
	 * 
	 * 描述: 添加到缓存中
	 * 没有则添加，有则放弃添加，返回false
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean add(final String key, final Object value, final Date expiry);
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(final String key, final Object value);
	
	/**
	 * 
	 * 描述: 替换到缓存中
	 * 没有则放弃，返回false；有则替换
	 * @author qye.zheng
	 * @param key
	 * @param value
	 * @param expiry 过期时间
	 * @return
	 */
	public boolean replace(final String key, final Object value, final Date expiry);
	
	/**
	 * 
	 * 描述: 根据key从缓存中移除对象
	 * @author qye.zheng
	 * 
	 * @param key
	 * @return
	 */
	public Object delete(final String key);
	
	/**
	 * 
	 * 描述: 根据key从缓存中获取对象
	 * @author qye.zheng
	 * @param key
	 * @return
	 */
	public Object get(final String key);
}
