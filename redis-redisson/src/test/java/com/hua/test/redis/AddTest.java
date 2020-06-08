/**
 * 描述: 
 * AddTest.java
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

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;

import redis.clients.jedis.ListPosition;
import redis.clients.jedis.params.SetParams;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * AddTest
 */
public final class AddTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAdd() {
		try {
			// 
			User user = new User();
			user.setId("20151230");
			user.setUsername("世界之窗");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊");
			user.setPassword("123456");
			user.setValid(true);
			user.setLastLoginTime(new Date());
			// 
			String value = JacksonUtil.writeAsString(user);
			String key = "COM:USER:2015:" + user.getId();
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			String nxx = "NX";
			//nxx = "XX";
			// expx expire time units: EX = seconds; PX = milliseconds
			String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			String statusCode = jedis.set(key, value, param);
			log.info("testAdd =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testAdd =====> ", e);
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
	public void testAddList() {
		try {
			// 
			User user = null;
			String key = "COM:USERS";
			String value = null;
			Long statusCode = null;
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
			statusCode =	jedis.lpush(key, value);
			log.info("testSetList =====> statusCode = " + statusCode);
			
			user = new User();
			user.setId("20151229");
			user.setUsername("世界之窗2");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊2");
			user.setPassword("123457");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			index = 1L;
			statusCode =	jedis.lpush(key, value);
			log.info("testSetList =====> statusCode = " + statusCode);
			
			user = new User();
			user.setId("20151230");
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			index = 2L;
			statusCode =	jedis.lpush(key, value);
			log.info("testSetList =====> statusCode = " + statusCode);
			jedis.linsert(key, ListPosition.AFTER, "", value);
			
		} catch (Exception e) {
			log.error("testAddList =====> ", e);
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
	public void testAddListNumber() {
		try {
			String key = "COM:NUMBER";
			Long statusCode = null;

			statusCode =	jedis.lpush(key, "10");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
			statusCode =	jedis.lpush(key, "5");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
			statusCode =	jedis.lpush(key, "9");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
			statusCode =	jedis.lpush(key, "7");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
			statusCode =	jedis.lpush(key, "45");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
			statusCode =	jedis.lpush(key, "31");
			log.info("testAddListNumber =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testAddListNumber =====> ", e);
		}
	}
	
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
	public void testListPop() {
		try {
			// 
			User user = null;
			String key = "COM:USERS";
			String value = null;
			String statusCode = null;
			long index = 0L;
			long length = 0L;
			
			value = jedis.lpop(key);
			log.info("testList =====> value = " + value);
			
			
			
		} catch (Exception e) {
			log.error("testListPop =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testListPush() {
		try {
			// 
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
			size =	jedis.lpush(key, value);
			log.info("testListPush =====> size = " + size);
			
			user = new User();
			user.setId("20151229");
			user.setUsername("世界之窗2");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊2");
			user.setPassword("123457");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			log.info("testListPush =====> size = " + size);
			
			user = new User();
			user.setId("20151230");
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			log.info("testListPush =====> size = " + size);
			
		} catch (Exception e) {
			log.error("testListPush =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAddHash() {
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
			String key = "COM:USER:2015:" + user.getId();
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			//String nxx = "NX";
			//nxx = "XX";
			// expx expire time units: EX = seconds; PX = milliseconds
			//String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			String statusCode = jedis.set(key, value, param);
			log.info("testAdd =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testAddHash =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBuild2List() {
		try {
			// 将一个或多个值插入表头
			User user = null;
			String key = "COM:USERS";
			String key1 = "COM:USERS1";
			String key2 = "COM:USERS2";
			String value = null;
			int i = 0;
			// 添加之后，集合的大小
			Long size = null;
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗2");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊2");
			user.setPassword("123457");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testLPush =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗3");
			user.setLastLoginIp("192.168.6.39");
			user.setNickname("绵羊3");
			user.setPassword("123458");
			user.setValid(false);
			// 
			value = JacksonUtil.writeAsString(user);
			size =	jedis.lpush(key, value);
			size =	jedis.lpush(key1, value);
			size =	jedis.lpush(key2, value);
			log.info("testBuild2List =====> size = " + size);
			
		} catch (Exception e) {
			log.error("testBuild2List =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBuildSet() {
		try {
			// 将一个或多个值插入表头
			User user = null;
			String key = "COM:SET:USERS";
			String key1 = "COM:SET:USERS1";
			String key2 = "COM:SET:USERS2";
			String value = null;
			int i = 0;
			// 1-成功，0-失败
			Long result = null;
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			result =	jedis.sadd(key, value);
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			result =	jedis.sadd(key, value);
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
			
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			result =	jedis.sadd(key, value);
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
		} catch (Exception e) {
			log.error("testBuildSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBuildSortedSet() {
		try {
			/*
			 * 将一个或多个元素放入有序集合key中
			 * 若元素已经存在，则更新score，并重新插入
			 * 这个元素，来保证该元素在集合中的正确位置
			 * 若score相同，则按照字符串来排序
			 */
			String key = "COM:SORTEDSET:USERS";
			String member = null;
			double score  = 1.0;
			//char c = 'a';
			char c = 'A';
			for (int i = 0; i < 26; i++)
			{
				member = String.valueOf(c);
				jedis.zadd(key, score, member);
				score++;
				c++;
			}
			
			
		} catch (Exception e) {
			log.error("testBuildSortedSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
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
			String key = "COM:USER:2015:" + user.getId();
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			String nxx = "NX";
			//nxx = "XX";
			// expx expire time units: EX = seconds; PX = milliseconds
			String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			
			String statusCode = jedis.set(key, value, param);
			log.info("testAdd =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("test =====> ", e);
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
			char c = 'a';
			c++;
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
