/**
  * @filename ICache.java
  * @description 缓存
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import java.util.Date;

 /**
 * @type ICache
 * @description 
 * @author qye.zheng
 */
public interface ICache {

	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public boolean isLocal();

	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public boolean isAvailable();

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public boolean keyExists(String key);

	/**
	 * 
	 * @description 
	 * @param key
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	public boolean put(String key, Object value);

	/**
	 * 
	 * @description 
	 * @param key
	 * @param value
	 * @param expireDt
	 * @return
	 * @author qye.zheng
	 */
	public boolean put(String key, Object value, Date expireDt);

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public Object get(String key);

	/**
	 * 
	 * @description 
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	public boolean delete(String key);
}
