/**
  * @filename UserRemoveListener.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.listener;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.hua.bean.User;

 /**
 * @type UserRemoveListener
 * @description  
 * @author qye.zheng
 */
public class UserRemoveListener implements RemovalListener<String, User> {

	/**
	 * 和删除动作同步执行，若需要成异步:
	 * RemovalListeners.asynchronous(final RemovalListener<K, V> listener, 
	 * final Executor executor)
	 */
	
	/**
	 * @description 
	 * @param notification
	 * @author qye.zheng
	 */
	public void onRemoval(RemovalNotification<String, User> notification) {
		System.out.println("key = " + notification.getKey() 
				+ ", value = " + notification.getValue().getUsername() + " has synchronized remove");
	}

}
