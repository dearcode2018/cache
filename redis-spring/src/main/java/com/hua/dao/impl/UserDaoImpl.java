/**
 * @filename UserDaoImpl.java
 * @description 
 * @version 1.0
 * @author qianye.zheng
 */
package com.hua.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.hua.bean.User;
import com.hua.dao.AbstractRedisDao;
import com.hua.dao.UserDao;

/**
 * @type UserDaoImpl
 * @description
 * @author qianye.zheng
 */
public final class UserDaoImpl extends AbstractRedisDao<String, User> implements
		UserDao
{

	/**
	 * @description
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
	public boolean add(final User user)
	{
		boolean flag = redisTemplate.execute(new RedisCallback<Boolean>()
		{
			/**
			 * @description
			 * @param connection
			 * @return
			 * @throws DataAccessException
			 * @author qianye.zheng
			 */
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException
			{
				final RedisSerializer<String> serializer = getRedisSerializer();
				final byte[] key = serializer.serialize(user.getId());
				final byte[] name = serializer.serialize(user.getUsername());

				// setNX non exist 不存在才增加
				return connection.setNX(key, name);
			}
		});

		return flag;
	}

	/**
	 * @description
	 * @param users
	 * @return
	 * @author qianye.zheng
	 */
	public boolean add(final List<User> users)
	{
		Assert.notEmpty(users);
		boolean flag = redisTemplate.execute(new RedisCallback<Boolean>()
		{
			/**
			 * @description
			 * @param connection
			 * @return
			 * @throws DataAccessException
			 * @author qianye.zheng
			 */
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException
			{
				final RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = null;
				byte[] name = null;
				for (User user : users)
				{
					key = serializer.serialize(user.getId());
					name = serializer.serialize(user.getUsername());
					// setNX non exist 不存在才增加
					connection.setNX(key, name);
				}

				return true;
			}
		}, false, true);

		return flag;
	}

	/**
	 * @description
	 * @param key
	 * @author qianye.zheng
	 */
	public void delete(final String key)
	{
		final List<String> keys = new ArrayList<String>();
		keys.add(key);
		delete(keys);
	}

	/**
	 * @description
	 * @param keys
	 * @author qianye.zheng
	 */
	public void delete(final List<String> keys)
	{
		redisTemplate.delete(keys);
	}

	/**
	 * @description
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
	public boolean update(final User user)
	{
		final String key = user.getId();
		if (null == get(key))
		{
			
			throw new RuntimeException("key = " + key + ", 数据不存在");
		}
		
		boolean flag = redisTemplate.execute(new RedisCallback<Boolean>()
		{
			/**
			 * @description
			 * @param connection
			 * @return
			 * @throws DataAccessException
			 * @author qianye.zheng
			 */
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException
			{
				final RedisSerializer<String> serializer = getRedisSerializer();
				final byte[] key = serializer.serialize(user.getId());
				final byte[] name = serializer.serialize(user.getUsername());
				connection.set(key, name);
				
				return true;
			}
		});

		return flag;
	}

	/**
	 * @description
	 * @param key
	 * @return
	 * @author qianye.zheng
	 */
	public User get(final String key)
	{
		User result = redisTemplate.execute(new RedisCallback<User>()
		{
			/**
			 * @description 
			 * @param connection
			 * @return
			 * @throws DataAccessException
			 * @author qianye.zheng
			 */
			public User doInRedis(RedisConnection connection)
					throws DataAccessException
			{
				User u = null;
				final RedisSerializer<String> serializer = getRedisSerializer();
				final byte[] key_ = serializer.serialize(key);
				final byte[] value = connection.get(key_);
				if (null != value)
				{
					// 构造、组装对象
					u = new User();
					u.setId(key);
					final String name = serializer.deserialize(value);
					u.setUsername(name);
				}
				
				return u;
			}
		});
		
		return result;
	}

}
