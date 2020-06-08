/**
 * 描述: 
 * RedisLockTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.client;

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
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hua.ApplicationStarter;
import com.hua.test.BaseTest;
import com.hua.util.RandomUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RedisLockTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class RedisLockTest extends BaseTest {

	
	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@ExtendWith(SpringExtension.class)
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@ExtendWith(SpringExtension.class)
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	//@Resource
	//private UserController userController;
	
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */
	
	private Jedis client;

	/**
	 * redis锁
	 * 1) 用set NX 实现: 不存在才设置，带有失效时间，避免某个线程不释放锁，设置随机值，
	 * 用于释放时核对. 释放锁用lua脚本实现，获取锁不考虑冲入的情况，无须用lua脚本
	 * 2) redisson实现
	 * 3) RedLock，红锁，可通过redisson实现
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testGetLock() {
		try {
			String key = "some:lock:2020";
			String randomVal = RandomUtil.randomAlphabetic(10);
			System.out.println("randomVal = " + randomVal);
			long expireMillisec = 600000;
			boolean success = lock(key, expireMillisec, randomVal);
			assertTrue(success);
		} catch (Exception e) {
			log.error("testGetLock =====> ", e);
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
	public void testReleaseLock() {
		try {
			String key = "some:lock:2020";
			String randomVal = "TblXAyDsxQ";
			boolean success = release(key, randomVal);
			assertTrue(success);
		} catch (Exception e) {
			log.error("testReleaseLock =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 加锁/获取锁
	 * @param key 键
	 * @param expireMillisec 存活时间，单位: 毫秒
	 * @param randomVal 随机值，释放锁时核对
	 * @return true-成功，false-失败
	 * @author qianye.zheng
	 */
	public boolean lock(final String key, final long expireMillisec, final String randomVal) {
		final SetParams params = new SetParams();
		params.nx();
		params.px(expireMillisec);
		final String result = client.set(key, randomVal, params);
		
		return "OK".equalsIgnoreCase(result);
	}
	
	/**
	 * 
	 * @description 
	 * @param key
	 * @param randomVal
	 * @return
	 * @author qianye.zheng
	 */
	public boolean release(final String key, final String randomVal) {
		final String script = "if redis.call('GET', KEYS[1]) == ARGV[1] then "
				+ "return redis.call('DEL', KEYS[1]); " +
				"else "
				+ "return 0;"
				+ "end";
		final long result = (long) client.eval(script, Arrays.asList(key), Arrays.asList(randomVal));
		
		return result == 1;
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
	 * @description 创建一个客户端
	 * @return
	 * @author qianye.zheng
	 */
	private Jedis createClient() {
		/**
		 * socketTimeout 就是readTimeout
		 */
		final Jedis jedis = new Jedis(redisProperties.getHost(), redisProperties.getPort(), 
				redisProperties.getConnectTimeoutMillisec(), redisProperties.getReadTimeoutMillisec());
		jedis.auth(redisProperties.getPassword());
		jedis.select(redisProperties.getDatabase());
		
		return jedis;
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
		// 创建客户端
		client = createClient();
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
		// 关闭连接，客户端失效
		client.disconnect();
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
		
	}

}
