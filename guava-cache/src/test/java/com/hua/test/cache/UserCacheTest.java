/**
 * 描述: 
 * UserCacheTest.java
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

import java.util.concurrent.Callable;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hua.bean.User;
import com.hua.cache.UserCacheLoader;
import com.hua.cache.UserCallableCallbackA;
import com.hua.cache.UserCallableCallbackB;
import com.hua.test.BaseTest;
import com.hua.util.GuavaCacheUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * UserCacheTest
 */
public final class UserCacheTest extends BaseTest {

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
	public void testCacheLoader() {
		try {
			CacheLoader<String, User> cacheLoader = new UserCacheLoader();
			LoadingCache<String, User> loadingCache = GuavaCacheUtil.buildLoadingCache(cacheLoader);
			
			User user = new User();
			user.setUsername("user put in");
			loadingCache.put("zhangsan", user);
			
			log.info("testCacheLoader =====> " + loadingCache.get("zhangsan").getUsername());
			
			log.info("testCacheLoader =====> " + loadingCache.get("lisi").getUsername());
		} catch (Exception e) {
			log.error("testCacheLoader =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRemovalListener() {
		try {
			CacheLoader<String, User> cacheLoader = new UserCacheLoader();
			LoadingCache<String, User> loadingCache = GuavaCacheUtil.buildLoadingCache(cacheLoader);
			String key = "zhangsan";
			User user = new User();
			user.setUsername("user put in");
			loadingCache.put(key, user);
			
			// 删除之前
			log.info("testRemovalListener =====> " + loadingCache.get("zhangsan").getUsername());
			
			// 删除动作
			loadingCache.invalidate(key);
			
			// 删除之后
			log.info("testRemovalListener =====> " + loadingCache.get("zhangsan").getUsername());
			
			
		} catch (Exception e) {
			log.error("testRemovalListener =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallableCallback() {
		try {
			Cache<String, User> cache = GuavaCacheUtil.buildCache();

			/**
			 * 每次 cache.get 都会有一个回调，灵活性更强
			 */
			Callable<User> callableA = new UserCallableCallbackA();
			log.info("testCallableCallback =====> " + cache.get("a", callableA).getUsername());
			
			
			Callable<User> callableB = new UserCallableCallbackB();
			log.info("testCallableCallback =====> " + cache.get("b", callableB).getUsername());
			
		} catch (Exception e) {
			log.error("testCallableCallback =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCacheInvalidate() {
		try {
			Cache<String, User> cache = GuavaCacheUtil.buildCache();
			
			User user = new User();
			user.setUsername("user put in");
			cache.put("a", user);
			
			/**
			 * 当在缓存中无法获取到指定key的值是，
			 * 就从 CallableCallback中去获取
			 */
			Callable<User> callableA = new UserCallableCallbackA();
			log.info("testCallableCallback =====> " + cache.get("a", callableA).getUsername());
			
			
			Callable<User> callableB = new UserCallableCallbackB();
			log.info("testCallableCallback =====> " + cache.get("b", callableB).getUsername());
			
			
			
		} catch (Exception e) {
			log.error("testCacheInvalidate =====> ", e);
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
