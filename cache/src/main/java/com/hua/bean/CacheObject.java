package com.hua.bean;

import java.io.Serializable;
import java.util.Date;

import com.hua.util.DateTimeUtil;

/**
 * 
 * @type CacheObject
 * @description  缓存对象
 * @author qye.zheng
 */
public class CacheObject implements Serializable
{
	private static final long serialVersionUID = -3695513855839201605L;
	
	/* 缓存的key */
	private String key;
	
	/* 失效日期时间 */
	private Date expireDt;
	
	/* 缓存的对象 */
	private Object object;

	/**
	 * 
	 * @description 构造方法
	 * @param key
	 * @param object
	 * @author qye.zheng
	 */
	public CacheObject(String key, Object object)
	{
		// 设置一个超长失效日期时间
		this(key, object, DateTimeUtil.autoParse("2100-12-30"));
	}

	/**
	 * 
	 * @description 构造方法
	 * @param key
	 * @param object
	 * @param expireDt
	 * @author qye.zheng
	 */
	public CacheObject(String key, Object object, Date expireDt)
	{
		this.key = key;
		this.object = object;
		this.expireDt = expireDt;
	}

	public String getKey()
	{
		return key;
	}

	public Date getExpireDt()
	{
		return expireDt;
	}

	public void setExpireDt(Date value)
	{
		expireDt = value;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object value)
	{
		object = value;
	}

	/**
	 * 
	 * @description 判断是否失效 -> 是否可用
	 * @return
	 * @author qye.zheng
	 */
	public boolean isAvailable()
	{
		// 没有设置失效日期时间 或 失效日期时间的值 大于 当前日期时间
		return expireDt == null || expireDt.getTime() >= new Date().getTime();
	}

	@Override
	public String toString()
	{
		return "CacheObject [key = " + key + ", expireDt = " + expireDt + ", object = " + object + "]";
	}
}
