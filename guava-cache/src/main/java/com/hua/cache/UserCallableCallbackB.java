/**
  * @filename UserCallableCallbackB.java
  * @description  
  * @version 1.0
  * 
  * @author qye.zheng
 */
package com.hua.cache;

import java.util.concurrent.Callable;

import com.hua.bean.User;

 /**
 * @type UserCallableCallbackB
 * @description  
 * @author qye.zheng
 */
public final class UserCallableCallbackB implements Callable<User> {

	/**
	 * @description 
	 * @return
	 * @throws Exception
	 * @author qye.zheng
	 */
	public User call() throws Exception {
		User user = new User();
		user.setUsername("UserCallableCallbackB");
		
		return user;
	}

}
