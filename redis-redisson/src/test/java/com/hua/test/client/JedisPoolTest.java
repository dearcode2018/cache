/**
 * 描述: 
 * JedisPoolTest.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.hua.configuration.RedisProperties;
import com.hua.test.BaseTest;
import com.hua.util.BeanUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JedisPoolTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class JedisPoolTest extends BaseTest {

	
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
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testPool() {
		try {
			
			/**
			 * active(活跃): 就是所有当前已借出的对象
			 */
			JedisPool pool = createPool();
			
			System.out.println("租借之前: active = " + pool.getNumActive());
			// 租借资源
			Jedis client = pool.getResource();
			String response = client.ping();
			System.out.println(response);
			
			System.out.println("归还之前: active = " + pool.getNumActive());
			/*
			 * 关闭: 归还连接池
			 */
			client.close();
			
			// 关闭和服务端的连接，归还给连接池不是调用此方法
			//client.disconnect();
			
			System.out.println("归还之后: active = " + pool.getNumActive());
			
			// 关闭连接池
			pool.close();
		} catch (Exception e) {
			log.error("testPool =====> ", e);
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
	public void testPoolRuning() {
		try {
			JedisPool pool = createPool();
			// 租借资源
			//Jedis client = pool.getResource();
			/*
			 * 在连接池中增加多少个对象
			 * 直接扩大连接池的容量
			 */
			//pool.addObjects(0);
			final List<Jedis> clients = new ArrayList<>();
			int i = 1000;
			while (i > 0) {
				System.out.println("borrow before: active = " + pool.getNumActive() + ", idle = " + pool.getNumIdle() + ", waiters = " + pool.getNumWaiters());
				// 租借资源
				
				/*
				 * 资源耗尽抛出此异常:
				 * redis.clients.jedis.exceptions.JedisExhaustedPoolException: Could not get a resource since the pool is exhausted
				 */
				 clients.add(pool.getResource());
					System.out.println("borrow after: active = " + pool.getNumActive() + ", idle = " + pool.getNumIdle() + ", waiters = " + pool.getNumWaiters());
				TimeUnit.SECONDS.sleep(1);
				i--;
			}
			
			// 关闭连接池 destroy 或 close
			pool.close();
			//pool.destroy();
		} catch (Exception e) {
			log.error("testPoolRuning =====> ", e);
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
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	private JedisPool createPool() {
		final JedisPoolConfig config = new JedisPoolConfig();
		final RedisProperties.Pool poolConfig = redisProperties.getPool();
		try {
			BeanUtil.copyProperties(config, poolConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* config.setMaxIdle(poolConfig.getMaxIdle());
		config.setMaxTotal(poolConfig.getMaxTotal());
		config.setMaxWaitMillis(poolConfig.getMaxWaitMillis());
		config.setTestOnBorrow(poolConfig.isTestOnBorrow());
		config.setEvictorShutdownTimeoutMillis(poolConfig.getEvictorShutdownTimeoutMillis());
		config.setJmxEnabled(poolConfig.isJmxEnabled());
		config.setFairness(poolConfig.isFairness());
		config.setLifo(poolConfig.isLifo());
		config.setMinIdle(poolConfig.getMinIdle());
		config.setMinEvictableIdleTimeMillis(poolConfig.getMinEvictableIdleTimeMillis());
		config.setTimeBetweenEvictionRunsMillis(poolConfig.getTimeBetweenEvictionRunsMillis());
		config.setTestOnReturn(poolConfig.isTestOnReturn()); */
		final JedisPool pool = new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(), 
				redisProperties.getConnectTimeoutMillisec(), redisProperties.getReadTimeoutMillisec(), redisProperties.getPassword(), redisProperties.getDatabase(), null);
		
		return pool;
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
		
	}

}
