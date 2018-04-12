/**
  * @filename UserDao.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.dao;

import java.util.List;

import com.hua.bean.User;

 /**
 * @type UserDao
 * @description 
 * @author qianye.zheng
 */
public interface UserDao {

	/**
	 * 
	 * @description 新增 
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
    boolean add(final User user);  

    /**
     * 
     * @description 批量新增 使用pipeline方式 
     * @param users
     * @return
     * @author qianye.zheng
     */
    boolean add(final List<User> users);  
      
    /**
     * 
     * @description 删除 
     * @param key
     * @author qianye.zheng
     */
    void delete(final String key);  
      
    /**
     * 
     * @description 删除多个 
     * @param keys
     * @author qianye.zheng
     */
    void delete(final List<String> keys);  
      
    /**
     * 
     * @description 修改 
     * @param user
     * @return
     * @author qianye.zheng
     */
    boolean update(final User user);  

    /**
     * 
     * @description 通过key获取 
     * @param key
     * @return
     * @author qianye.zheng
     */
    User get(final String key);  
}
