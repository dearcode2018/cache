/**
  * @filename BaseCache.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.cache.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hua.util.StringUtil;

 /**
 * @type BaseCache
 * @description 缓存 - 公共部分
 * @author qye.zheng
 */
public class BaseCache implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/* 是否强制 md5 编码 */
	protected boolean firstKeyIsForceMd5;

	/**
	 * @param firstKeyIsForceMd5 the firstKeyIsForceMd5 to set
	 */
	public final void setFirstKeyIsForceMd5(boolean firstKeyIsForceMd5) {
		this.firstKeyIsForceMd5 = firstKeyIsForceMd5;
	}
	
	/**
	 * 
	 * @description 初始化
	 * @author qye.zheng
	 */
	public void init()
	{
		
	}
	
	/**
	 * 
	 * @description 获取
	 * @param key
	 * @return
	 * @author qye.zheng
	 */
	protected String getFirstKey(String key)
	{
		if (firstKeyIsForceMd5)
		{
			
		}
		
		return key;
	}
	
	/**
	 * 
	 * @description 判断 键 是否为空
	 * @param key 键
	 * @return 空-true，非空-false
	 * @author qye.zheng
	 */
	protected boolean keyIsEmpty(final String key)
	{
		if (StringUtil.isEmpty(key))
		{
			log.error("The key [" + key + "] is empty.");
			
			return true;
		}
		
		return false;
	}

}
