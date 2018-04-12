/**
  * @filename CacheMonitor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.client;

import redis.clients.jedis.JedisMonitor;

 /**
 * @type CacheMonitor
 * @description 
 * @author qianye.zheng
 */
public class CacheMonitor extends JedisMonitor
{

	/**
	 * @description 
	 * @param command
	 * @author qianye.zheng
	 */
	@Override
	public void onCommand(String command)
	{
		System.out.println("redis server accept command: " + command);
	}

}
