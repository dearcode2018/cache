/**
  * @filename CustomExpiry.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;


import java.util.concurrent.TimeUnit;

import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expiry;

/**
 * @type CustomExpiry
 * @description 
 * @author qianye.zheng
 */
public class CustomExpiry implements Expiry<String, String>
{

	private Integer minute = 1;

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Duration getExpiryForCreation(String key, String value)
	{
		return new Duration(minute, TimeUnit.MINUTES);
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Duration getExpiryForAccess(String key, String value)
	{
		return new Duration(minute, TimeUnit.MINUTES);
	}

	/**
	 * @description 
	 * @param key
	 * @param oldValue
	 * @param newValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Duration getExpiryForUpdate(String key, String oldValue,
			String newValue)
	{
		return new Duration(minute, TimeUnit.MINUTES);
	}
	
	

}
