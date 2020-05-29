/**
* UserType.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.entity;

/**
 * 描述: 
 * @author qye.zheng
 * UserType
 */
public enum UserType {

	ADMIN(0, "管理员"),
	NORMAL(1, "普通用户"),
	GUEST(2, "游客");
	
	private int type;
	
	private String remark;

	/**
	 * @description 构造方法
	 * @param type
	 * @param remark
	 * @author qianye.zheng
	 */
	private UserType(int type, String remark) {
		this.type = type;
		this.remark = remark;
	}

	/**
	* @return the type
	*/
	public int getType() {
		return type;
	}

	/**
	* @param type the type to set
	*/
	public void setType(int type) {
		this.type = type;
	}

	/**
	* @return the remark
	*/
	public String getRemark() {
		return remark;
	}

	/**
	* @param remark the remark to set
	*/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
