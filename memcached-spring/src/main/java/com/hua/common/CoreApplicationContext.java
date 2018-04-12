package com.hua.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 需要在 ioc 中配置
 * @type CoreApplicationContext
 * @description  spring 应用上下文
 * @author qye.zheng
 */
public class CoreApplicationContext implements ApplicationContextAware
{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	private static CoreApplicationContext coreApplicationContext;
	
	private ApplicationContext applicationContext;

	public CoreApplicationContext()
	{
		coreApplicationContext = this;
	}

	public static ApplicationContext getApplicationContext()
	{
		return coreApplicationContext.applicationContext;
	}

	public void setApplicationContext(ApplicationContext appCtx) throws BeansException
	{
		log.info("Initalizing ApplicationContext......");
		applicationContext = appCtx;
	}
}
