/**
 * 描述: 
 * RedisTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.cache;

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

import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.cache.CacheManager;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;

import redis.clients.jedis.params.SetParams;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RedisTest
 */
public final class RedisTest extends BaseTest {

	private CacheManager cacheManager = CacheManager.getInstance();
	
	private String key = "r_test_1";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRedis() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRedis =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRedisAvailable() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRedisAvailable =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPut() {
		try {
			boolean flag = cacheManager.put(key, "hello redis");
			log.info("testPut =====> flag = " + flag);
		} catch (Exception e) {
			log.error("testPut =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPutExpire() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 1);
			Date expireDate = cal.getTime();
			boolean flag = cacheManager.put(key, "hello redis expire", expireDate);
			log.info("testPutExpire =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testPutExpire =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 测试 @Deprecated 标注的字段问题
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeprecatedField() {
		try {
			// 
			User user = new User();
			user.setId("20151231");
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
			//String nxx = "NX";
			//nxx = "XX";
			// expx expire time units: EX = seconds; PX = milliseconds
			//String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			//String statusCode = jedis.set(key, value, nxx, expx, time);
			String statusCode = jedis.set(key.getBytes(), value.getBytes(), param);
			log.info("testAdd =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testAdd =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			Object result = cacheManager.get(key);
			

			log.info("testGet =====> result = " + result);
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			boolean flag = cacheManager.delete(key);
			log.info("testDelete =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testKeyExists() {
		try {
			boolean flag = cacheManager.keyExists(key);
			log.info("testKeyExists =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testKeyExists =====> ", e);
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
