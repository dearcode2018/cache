/**
  * @filename SentinelConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.net.UnknownHostException;
import java.time.Duration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @type SentinelConfiguration
 * @description
 * @author qianye.zheng
 */
@Configuration
public class SentinelConfiguration
{
	@Resource
	private SentinelProperties sentinelProperties;

	/**
	 * 
	 * @description
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
	@Bean("sentinelTemplate")
	public StringRedisTemplate stringRedisTemplate()
	{
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(connectionFactory());

		return template;
	}

	/**
	 * 
	 * @description
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RedisConnectionFactory connectionFactory()
	{
		/**
		 * RedisConfiguration实现
		 * 1) RedisStandaloneConfiguration 单机
		 * 2) RedisSocketConfiguration 套接字
		 * 3) RedisStaticMasterReplicaConfiguration 静态主从
		 * 4) RedisSentinelConfiguration 哨兵
		 * 5) RedisClusterConfiguration 集群
		 * 
		 */
		
		final RedisSentinelConfiguration re = new RedisSentinelConfiguration(sentinelProperties.getMaster(), sentinelProperties.getSentinelHostAndPorts());
		re.setPassword(sentinelProperties.getPassword());
		re.setDatabase(sentinelProperties.getDatabase());
		
		/* 池化，哨兵模式默认使用池 */
		final JedisClientConfiguration.JedisClientConfigurationBuilder  builder = JedisClientConfiguration.builder();
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(sentinelProperties.getPool().getMinIdle());
		poolConfig.setMaxIdle(sentinelProperties.getPool().getMaxIdle());
		poolConfig.setMaxTotal(sentinelProperties.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(sentinelProperties.getPool().getMaxWaitMillis());
		poolConfig.setTestOnCreate(true);
		builder.usePooling().poolConfig(poolConfig);
		// 读取超时时间
		builder.readTimeout(Duration.ofMillis(sentinelProperties.getReadTimeout())).connectTimeout(Duration.ofMillis(sentinelProperties.getConnectTimeout()));

		return new JedisConnectionFactory(re, builder.build());
	}

}
