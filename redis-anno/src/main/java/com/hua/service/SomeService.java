/**
  * @filename SomeService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import com.hua.bean.User;

 /**
 * @type SomeService
 * @description 
 * @author qianye.zheng
 */
public interface SomeService
{
	
	/**
	 * 
	 * @description
	 * @param id 用户id 
	 * @return
	 * @author qianye.zheng
	 */
	public User cache(final User user);
	
	/**
	 * 
	 * @description 
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
	public User put(final User user);
	
	/**
	 * 
	 * @description 
	 * @param id 用户id
	 * @return
	 * @author qianye.zheng
	 */
	public boolean evict(final String id);
	
}
