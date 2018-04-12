/**
 * 描述: 
 * ListTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.redis;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.BinaryClient.LIST_POSITION;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ListTest
 */
public final class ListTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testList() {
		try {
			// 
			User user = null;
			String key = "COM:USERS";
			String value = null;
			String statusCode = null;
			long index = 0L;
			long length = 0L;
			
			length = jedis.llen(key);
			log.info("testList =====> length = " + length);
			
			
		} catch (Exception e) {
			log.error("testList =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBLPop() {
		try {
			/*
			 * blocking 列表阻塞式弹出
			 * 当给定列表内没有任何元素可供弹出时，连接将被BLOP阻塞，
			 * 直到等待超时或发现可弹出元素为止
			 * 多个可选的集合来弹出，从左到右逐个集合弹出，
			 * 这个方法可以用在 多个候选集合的情况下，逐个弹出，直到弹完为止
			 */
			String key1 = "COM:USERS1";
			String key2 = "COM:USERS2";
			// 最后一个参数必须是 timeout
			String[] args = {key1, key2, "300"};
			// 返回key 和 value
			// 这个方法只弹出一个键值对(key/value)
			List<String> values = jedis.blpop(args);
			for (int i = 0; i < values.size(); i++)
			{
				// 这个方法只弹出一个键值对(key/value)
				if (0 == (i % 2))
				{ // 偶数是key
					System.out.println("key = " + values.get(i));
				} else
				{ // 奇数是value
					System.out.println("value = " + values.get(i));
				}
			}
		} catch (Exception e) {
			log.error("testBLPop =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBRPop() {
		try {
			/*
			 * blocking 列表阻塞式弹出
			 * 当给定列表内没有任何元素可供弹出时，连接将被BLOP阻塞，
			 * 直到等待超时或发现可弹出元素为止
			 */
			String key1 = "COM:USERS1";
			String key2 = "COM:USERS2";
			// 最后一个参数必须是 timeout
			String[] args = {key1, key2, "300"};
			// 返回key 和 value
			// 这个方法只弹出一个键值对(key/value)
			List<String> values = jedis.brpop(args);
			for (int i = 0; i < values.size(); i++)
			{
				// 这个方法只弹出一个键值对(key/value)
				if (0 == (i % 2))
				{ // 偶数是key
					System.out.println("key = " + values.get(i));
				} else
				{ // 奇数是value
					System.out.println("value = " + values.get(i));
				}
			}
			
		} catch (Exception e) {
			log.error("testBRPop =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBRPopLPush() {
		try {
			/*
			 * 将列表source中的最后一个元素(尾元素)弹出，并返回给客户端
			 * 将source弹出的元素插入到列表destination，作为destination列表的头元素
			 */
			String sourceKey = "COM:USERS1";
			String destinationKey = "COM:USERS2";
			int timeout = 300;
			String value = jedis.brpoplpush(sourceKey, destinationKey, timeout);
			System.out.println(value);
			
		} catch (Exception e) {
			log.error("testBRPopLPush =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLIndex() {
		try {
			// 根据索引获取值
			String key = "COM:USERS";
			Long index = 1L;
			String value = jedis.lindex(key, index);
			System.out.println(value);
		} catch (Exception e) {
			log.error("testLIndex =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLInsert() {
		try {
			// 
			User user = new User();
			user.setId("20151229");
			user.setUsername("世界之窗");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊");
			user.setPassword("123456");
			user.setValid(true);
			// 
			String value = JacksonUtil.writeAsString(user);
			String key = "COM:USERS";
			// 之前或之后 插入，
			jedis.linsert(key, LIST_POSITION.AFTER, "", value);
			
			
		} catch (Exception e) {
			log.error("testLInsert =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLLen() {
		try {
			String key = "COM:USERS";
			Long length = jedis.llen(key);
			log.info("testLLen =====> length = " + length);
			
		} catch (Exception e) {
			log.error("testLLen =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLPop() {
		try {
			/*
			 * 移除并返回列表key的头元素
			 */
			String key = "COM:USERS";
			String value = null;
			value = jedis.lpop(key);
			log.info("testLPop =====> value = " + value);
		} catch (Exception e) {
			log.error("testLPop =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLPush() {
		try {
			// 将一个或多个值插入表头
			User user = null;
			String key = "COM:USERS";
			String value = null;
			// 添加之后，集合的大小
			Long size = null;
			user = new User();
			user.setId("20151212");
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			log.info("testLPush =====> size = " + size);
			
		} catch (Exception e) {
			log.error("testLPush =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLPushX() {
		try {
			/*
			 * key 存在才做操作
			 */
			// 将一个或多个值插入表头
			User user = null;
			String key = "COM:USERS";
			//String key = "COM:USERS3";
			String value = null;
			// 添加之后，集合的大小
			Long size = null;
			user = new User();
			user.setId("20151228");
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpushx(key, value);
			log.info("testLPush =====> size = " + size);
		} catch (Exception e) {
			log.error("testLPushX =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLRange() {
		try {
			String key = "COM:USERS";
			Long start = 0L;
			// 包括
			Long end = 2L;
			List<String> values = jedis.lrange(key, start, end);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testLRange =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLRem() {
		try {
			String key = "COM:USERS";
			/*
			 * 
			count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
			count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
			count = 0 : 移除表中所有与 value 相等的值。
			 */
			int count = 0;
			String value = "a";
			Long result = jedis.lrem(key, count, value);
			log.info("testLRem =====> result = " + result);
		} catch (Exception e) {
			log.error("testLRem =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 在指定的位置设置值
	 * 该key必须存在，而且该位置必须有值，
	 * 或者说下标不能超过其现有的范围
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLSet() {
		try {
			// 
			User user = null;
			String key = "COM:USERS";
			String value = null;
			String statusCode = null;
			long index = 0L;
			
			user = new User();
			user.setId("20151228");
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.37");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			index = 0L;
			statusCode =	jedis.lset(key, index, value);
			log.info("testLSet =====> statusCode = " + statusCode);
		} catch (Exception e) {
			log.error("testLSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLTrim() {
		try {
			/**
			 * 对列表进行修剪，让列表只保留区间内的元素，
			 * 不在指定区间之内的元素都将被删除
			 */
			String key = "COM:USERS";
			Long start = 0L;
			// 包括
			Long end = 17L;
			String statusCode = jedis.ltrim(key, start, end);
			log.info("testLRem =====> statusCode = " + statusCode);
		} catch (Exception e) {
			log.error("testLTrim =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRPop() {
		try {
			String key = "COM:USERS";
			String value = null;
			
			value = jedis.rpop(key);
			log.info("testLPop =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testRPop =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRPopLPush() {
		try {
			/*
			 * 将列表source中的最后一个元素(尾元素)弹出，并返回给客户端
			 * 将source弹出的元素插入到列表destination，作为destination列表的头元素
			 */
			String sourceKey = "COM:USERS1";
			String destinationKey = "COM:USERS2";
			String value = jedis.rpoplpush(sourceKey, destinationKey);
			System.out.println(value);
			
		} catch (Exception e) {
			log.error("testRPopLPush =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRPush() {
		try {
			User user = null;
			String key = "COM:USERS";
			String value = null;
			// 添加之后，集合的大小
			Long size = null;
			user = new User();
			user.setId("20151228");
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.rpush(key, value);
			log.info("testRPush =====> size = " + size);
			
		} catch (Exception e) {
			log.error("testRPush =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRPushX() {
		try {
			/**
			 * 当key 不存在时 什么都不做
			 */
			User user = null;
			String key = "COM:USERS";
			String value = null;
			// 添加之后，集合的大小
			Long size = null;
			user = new User();
			user.setId("20151228");
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.rpushx(key, value);
			log.info("testRPushX =====> size = " + size);
		} catch (Exception e) {
			log.error("testRPushX =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
