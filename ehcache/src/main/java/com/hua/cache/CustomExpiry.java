/**
  * @filename CustomExpiry.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;

import java.time.Duration;
import java.util.function.Supplier;

import org.ehcache.expiry.ExpiryPolicy;

/**
 * @type CustomExpiry
 * @description 
 * @author qianye.zheng
 */
public class CustomExpiry implements ExpiryPolicy<String, String>
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
		return Duration.ofMinutes(minute);
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Duration getExpiryForAccess(String key,
			Supplier<? extends String> value)
	{
		return Duration.ofMinutes(minute);
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
	public Duration getExpiryForUpdate(String key,
			Supplier<? extends String> oldValue, String newValue)
	{
		return Duration.ofMinutes(minute);
	}

}
