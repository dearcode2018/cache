/**
 * 描述: 
 * User.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * User
 */
@Data
/* value: 命名空间，缓存前缀，后面跟id，例如 哈希 users:1234
 * 不指定命名空间，默认用getClass().getName()为默认前缀
 * timeToLive: 生命周期，单位: 秒，固定值，无法通过配置生效
 *  */
@RedisHash(value = "users", timeToLive = 20)
//@RedisHash
public class User {

	/*  @Indexed 二级索引: 每次保存/通过对象删除/过期移除操作 都会保存与之对应的索引 
	 * @Id 默认有索引
	 * 
	 * 
	 * */
	
	@Id
	private String id;
	
	/* 登录-用户名 (唯一) */
	@Indexed
	private String username;
	
	/* 昵称 (用于显示) */
	private String nickname;
	
	/* 登录-密码 */
	private String password;
	
	/* 用户类型 */
	private UserType type;
	
	/* 用户状态 - 是否有效 默认 : 有效 */
	private boolean valid = true;
	
	/* 上一次登录-时间 */
	private LocalDateTime lastLoginTime;
	
	/* 上一次登录-IP地址 */
	private String lastLoginIp;
	
	/* 复合类型，hash中对应的field 类似为 address.province address.subField */
	private Address address;
	
	private List<Integer> roles;
	
	private Map<Integer, String> roleResources;
	
	/**
	 * 
	 * @type Address
	 * @description 
	 * @author qianye.zheng
	 */
	@Data
	public static final class Address {
		
		private String province;
		
		private String city;
		
		private String detail;
		
	}

}
