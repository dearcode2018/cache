/**
  * @filename CacheEnum.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.constant;

 /**
 * @type CacheEnum
 * @description 
 * @author qianye.zheng
 */
public enum CacheEnum {

	MASTER("master", "masterRedisTemplate", "主库"),
	
	REPLICA01("replica01", "replica01RedisTemplate", "从库1"),
	
	REPLICA02("replica02", "replica02RedisTemplate", "从库2");
	
	/* 配置前缀 */
	private String prefix;
	
	/* Bean名称 */
	private String beanName;
	
	private String remark;

	/**
	 * @description 构造方法
	 * @param prefix
	 * @param beanName
	 * @param remark
	 * @author qianye.zheng
	 */
	private CacheEnum(String prefix, String beanName, String remark) {
		this.prefix = prefix;
		this.beanName = beanName;
		this.remark = remark;
	}

	/**
	* @return the prefix
	*/
	public String getPrefix() {
		return prefix;
	}

	/**
	* @return the beanName
	*/
	public String getBeanName() {
		return beanName;
	}

	/**
	* @return the remark
	*/
	public String getRemark() {
		return remark;
	}
	
	
}
