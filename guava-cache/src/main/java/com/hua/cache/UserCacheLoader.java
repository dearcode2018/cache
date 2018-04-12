/**
  * @filename UserCacheLoader.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import com.google.common.cache.CacheLoader;
import com.hua.bean.User;

 /**
 * @type UserCacheLoader
 * @description  
 * @author qye.zheng
 */
public final class UserCacheLoader extends CacheLoader<String, User> {

	/**
	 * @description 
	 * @param key
	 * @return
	 * @throws Exception
	 * @author qye.zheng
	 */
	@Override
	public User load(String key) throws Exception {
		User user = new User();
		user.setUsername(key);
		
		return user;
	}

}
