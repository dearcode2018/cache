/**
  * @filename RedisMultiProperties.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @type RedisMultiProperties
 * @description 多个配置，支持任意扩展
 * @author qianye.zheng
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisMultiProperties 
{
	
	private Map<String, RedisProperties> multi;
	
}
