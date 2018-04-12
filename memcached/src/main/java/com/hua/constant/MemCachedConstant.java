/**
 * 描述: 
 * MemCachedConstant.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant;

/**
 * 描述: 缓存 - 常量
 * @author qye.zheng
 * MemCachedConstant
 */
public interface MemCachedConstant 
{
	
	// # separator is ,
	/* 服务器列表 */
	String SERVERS = "cache.memcached.servers";
	
	// # separator is ,
	/* 服务器权重 */
	String WEIGHTS ="cache.memcached.wights";
	
	// 初始连接数
	String INIT_CONNECT = "cache.memcached.initConnect";
	
	// 最小连接数
	String MIN_CONNECT = "cache.memcached.minConnect";
	
	// 最大连接数
	String MAX_CONNECT = "cache.memcached.maxConnect";
	
	// 最大处理时间
	String MAX_IDLE = "cache.memcached.maxIdle";
	
	// 主线程休眠时间
	String MAIN_THREAD_SLEEP = "cache.memcached.mainThreadSleep";
	
	// 是否使用nagle算法
	String IS_NAGLE = "cache.memcached.isNagle";
	
	// socket超时
	String SOCKET_TIME_OUT = "cache.memcached.socketTimeOut";
	
	// socket连接超时
	String SOCKET_CONNECT_TIME_OUT = "cache.memcached.socketConnectTimeOut";
	
	// 是否启用压缩
	String COMPRESS_ENABLE = "cache.memcached.compressEnable";
	
	// 超过此设置的数据都会被压缩 (单位 : kb)
	String COMPRESS_THRESHOLD = "cache.memcached.compressThreshold";
	
}
