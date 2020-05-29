/**
  * @filename UserRepository.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.repository;

import org.springframework.data.repository.CrudRepository;

import com.hua.entity.User;

/**
 * @type UserRepository
 * @description 
 * @author qianye.zheng
 */
/* 无需加任何注解和配置 */
public interface UserRepository extends CrudRepository<User, String>{

}
