/**
 * RedisClient.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.hua.bean.RedisConfig;

/**
 * RedisClient
 * 描述: Redis 客户端
 * @author  qye.zheng
 */
public final class RedisClient
{

	/* 非切片客户端连接 */
	private Jedis jedis;
	
	/* 非切片连接池 */
	private JedisPool jedisPool;
	
	/* 切片客户端连接 */
	private ShardedJedis shardedJedis;
	
	/* 切片连接池 */
	private ShardedJedisPool shardedJedisPool;
	
	private RedisConfig redisConfig;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public RedisClient() {
		this(new RedisConfig());
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public RedisClient(final RedisConfig redisConfig)
	{
		this.redisConfig = redisConfig;
		initialPool();
		initialShardedPool();
		jedis = jedisPool.getResource();
		shardedJedis = shardedJedisPool.getResource();
	}
	
	/**
	 * 
	 * @description 初始化非切片池
	 * @author qye.zheng
	 */
	private final void initialPool()
	{
		// 池基本配置
		final JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMaxTotal(redisConfig.getMaxActive());
		config.setMaxWaitMillis(redisConfig.getMaxWait());
		config.setTestOnBorrow(redisConfig.isTestOnBorrow());
		
		jedisPool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort());
	}
	
	/**
	 * 
	 * @description 初始化非切片池
	 * @author qye.zheng
	 */
	private final void initialShardedPool()
	{
		// 池基本配置
		final JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMaxTotal(redisConfig.getMaxActive());
		config.setMaxWaitMillis(redisConfig.getMaxWait());
		config.setTestOnBorrow(redisConfig.isTestOnBorrow());
		
		// slav 连接
		final List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo(redisConfig.getHost(), redisConfig.getPort(), "master");
		shards.add(jedisShardInfo);
		
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	/**
	 * @return the redisConfig
	 */
	public final RedisConfig getRedisConfig() {
		return redisConfig;
	}

	/**
	 * @param redisConfig the redisConfig to set
	 */
	public final void setRedisConfig(RedisConfig redisConfig) {
		this.redisConfig = redisConfig;
	}

	/**
	 * @return the jedis
	 */
	public final Jedis getJedis() {
		return jedis;
	}

	/**
	 * @return the shardedJedis
	 */
	public final ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

}
