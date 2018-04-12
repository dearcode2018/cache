/**
 * GuavaCacheUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.hua.bean.User;
import com.hua.listener.UserRemoveListener;

/**
 * GuavaCacheUtil
 * 描述: 
 * @author  qye.zheng
 */
public final class GuavaCacheUtil
{
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private GuavaCacheUtil()
	{
	}
	
	/**
	 * 
	 * @description CacheLoader方式 --> 不需延迟处理
	 * @param cacheLoader
	 * @return
	 * @author qye.zheng
	 */
	public static final <K, V> LoadingCache<K, V> buildLoadingCache(final CacheLoader<K, V> cacheLoader)
	{
		final CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		
		// 注册删除监听器
		final RemovalListener<String, User> removalListener = new UserRemoveListener();
		builder.removalListener(removalListener);
		
		return builder.build(cacheLoader);
	}

	/**
	 * 
	 * @description Callable Callback 方式 --> 需要延迟处理
	 * @return
	 * @author qye.zheng
	 */
	public static final <K, V> Cache<K, V> buildCache()
	{
		final CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
		
		// 注册删除监听器
		final RemovalListener<String, User> removalListener = new UserRemoveListener();
		builder.removalListener(removalListener);
		
		return builder.build();
	}
	
}
