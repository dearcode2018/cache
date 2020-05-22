/**
  * @filename SomeServiceImpl.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;




import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hua.bean.User;

 /**
 * @type SomeServiceImpl
 * @description 
 * @author qianye.zheng
 */
@Service
public class SomeServiceImpl implements SomeService
{

	/**
	 * Redis注解
	 * 
	 * value: 键，相当于redis中的key.
	 * key: value的子键
	 * condition: 执行条件
	 * 
	 * 
	 */
	
	/**
	 * @description 
	 * @param id 用户id
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	@Cacheable(value = "user:info", key = "#user.id", condition = "#user.id != 'none'", 
	sync = false, unless = "true")
	public User cache(final User user)
	{
		System.out.println("SomeServiceImpl.cache()");
		
		return user;
	}

	/**
	 * @description 
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	@CachePut(value = "user:info", key = "#user.id", condition = "", unless = "1")
	public User put(User user)
	{
		System.out.println("SomeServiceImpl.put()");
		
		return user;
	}

	/**
	 * @description 
	 * @param id 用户id
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	@CacheEvict(value = "user:info", key = "#id")
	public boolean evict(String id)
	{
		System.out.println("SomeServiceImpl.evict()");
		return true;
	}

}
