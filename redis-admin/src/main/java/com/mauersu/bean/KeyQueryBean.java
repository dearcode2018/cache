/**
  * @filename KeyQueryBean.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.mauersu.bean;

import lombok.Data;

/**
 * @type KeyQueryBean
 * @description 
 * @author qianye.zheng
 */
@Data
public class KeyQueryBean
{
	
	private String dataType;
	
	private Object values;

	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public KeyQueryBean()
	{
	}

	/**
	 * @description 构造方法
	 * @param dataType
	 * @param values
	 * @author qianye.zheng
	 */
	public KeyQueryBean(String dataType, Object values)
	{
		super();
		this.dataType = dataType;
		this.values = values;
	}

	
	
}
