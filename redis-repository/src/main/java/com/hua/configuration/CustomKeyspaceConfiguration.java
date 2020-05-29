/**
  * @filename CustomKeyspaceConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.Collections;

import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

import com.hua.entity.User;

/**
 * @type CustomKeyspaceConfiguration
 * @description 自定义redis仓库命名空间
 * @author qianye.zheng
 */
public class CustomKeyspaceConfiguration extends KeyspaceConfiguration {

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	protected Iterable<KeyspaceSettings> initialConfiguration() {
		
		return Collections.singletonList(new KeyspaceSettings(User.class, "userkeys", true));
	}
	
	
}
