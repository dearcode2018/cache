/**
 * 描述: 
 * RedisTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.cache;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
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
	//@DisplayName("test")
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
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}
