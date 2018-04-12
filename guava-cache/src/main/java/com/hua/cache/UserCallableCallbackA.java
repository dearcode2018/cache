/**
  * @filename UserCallableCallbackA.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache;

import java.util.concurrent.Callable;

import com.hua.bean.User;

 /**
 * @type UserCallableCallbackA
 * @description  
 * @author qye.zheng
 */
public final class UserCallableCallbackA implements Callable<User> {

	/**
	 * @description 
	 * @return
	 * @throws Exception
	 * @author qye.zheng
	 */
	public User call() throws Exception {
		User user = new User();
		user.setUsername("UserCallableCallbackA");
		
		return user;
	}

}
