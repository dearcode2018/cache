/**
  * @filename RedisConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.net.UnknownHostException;
import java.time.Duration;

import javax.annotation.Resource;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.hua.constant.CacheEnum;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @type RedisConfiguration
 * @description
 * @author qianye.zheng
 */
@Configuration
public class RedisConfiguration
{
	
	/**
	 * 一主多从
	 * 从实例，通过一个方法随机获取StringRedisTemplate
	 * 主: 写
	 * 从: 读
	 * 读写分类，写入后延迟读取，避免主从同步延迟.
	 * 
	 * 
	 * 
	 */
	@Resource
	private RedisMultiProperties multiProperties;

	/**
	 * 
	 * @description 主
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
	@Primary
	@Bean("masterRedisTemplate")
	public StringRedisTemplate masterRedisTemplate()
	{
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(masterFactory());

		return template;
	}

	/**
	 * 
	 * @description 从1
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
	@Bean("replica01RedisTemplate")
	public StringRedisTemplate replica01RedisTemplate()
	{
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(replica01Factory());

		return template;
	}
	
	/**
	 * 
	 * @description 从2
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
	@Bean("replica02RedisTemplate")
	public StringRedisTemplate replica02RedisTemplate()
	{
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(replica02Factory());

		return template;
	}
	
	/**
	 * 
	 * @description
	 * @return
	 * @author qianye.zheng
	 */
	private RedisConnectionFactory masterFactory()
	{
		/**
		 * RedisConfiguration 实现
		 * 1) RedisStandaloneConfiguration 单机
		 * 2) RedisSocketConfiguration 套接字
		 * 3) RedisStaticMasterReplicaConfiguration 静态主从
		 * 4) RedisSentinelConfiguration 哨兵
		 * 5) RedisClusterConfiguration 集群
		 * 
		 * 
		 */
		final RedisProperties prop = multiProperties.getMulti().get(CacheEnum.MASTER.getPrefix());
		final RedisStandaloneConfiguration re = new RedisStandaloneConfiguration(prop.getHost(), prop.getPort());
		re.setPassword(prop.getPassword());
		re.setDatabase(prop.getDatabase());
		/* 池化 */
		final JedisClientConfiguration.JedisClientConfigurationBuilder  builder = JedisClientConfiguration.builder();
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(prop.getPool().getMinIdle());
		poolConfig.setMaxIdle(prop.getPool().getMaxIdle());
		poolConfig.setMaxTotal(prop.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(prop.getPool().getMaxWaitMillis());
		poolConfig.setTestOnCreate(true);
		builder.usePooling().poolConfig(poolConfig);
		/*
		 * 连接|读取超时时间
		 * 连接超时时间: 发起连接到连接成功，最大能容忍的时间
		 * 读取超时时间: 连接期间，一次从开始读取到读取结束，最大能容忍的时间
		 */
		builder.connectTimeout(prop.getConnectTimeout()).readTimeout(prop.getReadTimeout());

		return new JedisConnectionFactory(re, builder.build());
	}
	
	/**
	 * 
	 * @description
	 * @return
	 * @author qianye.zheng
	 */
	private RedisConnectionFactory replica01Factory()
	{
		/**
		 * RedisConfiguration 实现
		 * 1) RedisStandaloneConfiguration 单机
		 * 2) RedisSocketConfiguration 套接字
		 * 3) RedisStaticMasterReplicaConfiguration 静态主从
		 * 4) RedisSentinelConfiguration 哨兵
		 * 5) RedisClusterConfiguration 集群
		 * 
		 * 
		 */
		final RedisProperties prop = multiProperties.getMulti().get(CacheEnum.REPLICA01.getPrefix());
		final RedisStandaloneConfiguration re = new RedisStandaloneConfiguration(prop.getHost(), prop.getPort());
		re.setPassword(prop.getPassword());
		re.setDatabase(prop.getDatabase());
		/* 池化 */
		final JedisClientConfiguration.JedisClientConfigurationBuilder  builder = JedisClientConfiguration.builder();
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(prop.getPool().getMinIdle());
		poolConfig.setMaxIdle(prop.getPool().getMaxIdle());
		poolConfig.setMaxTotal(prop.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(prop.getPool().getMaxWaitMillis());
		poolConfig.setTestOnCreate(true);
		builder.usePooling().poolConfig(poolConfig);
		// 读取超时时间
		builder.readTimeout(prop.getReadTimeout()).connectTimeout(prop.getConnectTimeout());

		return new JedisConnectionFactory(re, builder.build());			
	}
	
	/**
	 * 
	 * @description
	 * @return
	 * @author qianye.zheng
	 */
	private RedisConnectionFactory replica02Factory()
	{
		/**
		 * RedisConfiguration 实现
		 * 1) RedisStandaloneConfiguration 单机
		 * 2) RedisSocketConfiguration 套接字
		 * 3) RedisStaticMasterReplicaConfiguration 静态主从
		 * 4) RedisSentinelConfiguration 哨兵
		 * 5) RedisClusterConfiguration 集群
		 * 
		 * 
		 */
		final RedisProperties prop = multiProperties.getMulti().get(CacheEnum.REPLICA02.getPrefix());
		final RedisStandaloneConfiguration re = new RedisStandaloneConfiguration(prop.getHost(), prop.getPort());
		re.setPassword(prop.getPassword());
		re.setDatabase(prop.getDatabase());
			
		/* 池化 */
		final JedisClientConfiguration.JedisClientConfigurationBuilder  builder = JedisClientConfiguration.builder();
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(prop.getPool().getMinIdle());
		poolConfig.setMaxIdle(prop.getPool().getMaxIdle());
		poolConfig.setMaxTotal(prop.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(prop.getPool().getMaxWaitMillis());
		poolConfig.setTestOnCreate(true);
		builder.usePooling().poolConfig(poolConfig);
		// 读取超时时间
		builder.readTimeout(prop.getReadTimeout()).connectTimeout(prop.getConnectTimeout());

		return new JedisConnectionFactory(re, builder.build());
	}

}
