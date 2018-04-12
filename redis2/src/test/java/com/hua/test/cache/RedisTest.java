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

import com.hua.cache.CacheManager;
import com.hua.listener.RedisMsgPubSubListener;
import com.hua.test.BaseTest;
import com.hua.util.RedisClient;

import redis.clients.jedis.Jedis;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RedisTest
 */
public final class RedisTest extends BaseTest {

	private CacheManager cacheManager = CacheManager.getInstance();
	
	private String key = "r_test_1";
	
	String channel = "redisChatTest";
	
	/**
	 * 
	 * 描述: 订阅key过期消息
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubscribe() {
		try {
			//Jedis jedis = new Jedis("localhost");  
			Jedis jedis = new RedisClient().getJedis();
			RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
			jedis.subscribe(listener, "__keyevent@0__:expired");
		} catch (Exception e) {
			log.error("testSubscribe =====> ", e);
		}
	}
	
	
	/**
	 * testSubscribe2 testPublish联合测试发布和订阅消息，先订阅消息，在发布消息之后会收到消息
	 */
	
	/**
	 * 
	 * 描述: 订阅key过期消息
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubscribe2() {
		try {
			//Jedis jedis = new Jedis("localhost");  
			Jedis jedis = new RedisClient().getJedis();
			RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
			jedis.subscribe(listener, channel);
		} catch (Exception e) {
			log.error("testSubscribe2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 发布消息
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPublish() {
		try {
			Jedis jedis = new RedisClient().getJedis();
			jedis.publish(channel, "hello world 222");
		   // Thread.sleep(10000); 
			
		} catch (Exception e) {
			log.error("testPublish =====> ", e);
		}
	}
	
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
