/**
 * 描述: 
 * MemcachedTest.java
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

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.cache.CacheManager;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * MemcachedTest
 */
public final class MemcachedTest extends BaseTest {

	private String key = "TestKey";
	
	private Object value;
	
	private Date expireDt;
	
	private CacheManager instance = CacheManager.getInstance();
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcached() {
		try {
			key = "a";
			value = "a_value";
			instance.put(key, value);
			
			System.out.println(instance.get(key));
			
		} catch (Exception e) {
			log.error("testMemcached =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedPut() {
		try {
			key = "a";
			value = "a_value";
			instance.put(key, value);
			
			System.out.println(instance.get(key));
			
		} catch (Exception e) {
			log.error("testMemcached =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedPutExpire() {
		try {
			key = "a";
			value = "a_value";
			String dateTimeStr = "2015-02-11 09:14:05";
			expireDt = DateTimeUtil.parseDateTime(dateTimeStr);
			instance.put(key, value, expireDt);
			
			System.out.println(instance.get(key));
			
			// 通过线程休眠 延迟运行时间，模拟失效 - 效果
			Thread.sleep(60 * 1000);
			
			System.out.println(instance.get(key));
			
		} catch (Exception e) {
			log.error("testMemcached =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedDelete() {
		try {
			key = "a";
			value = "a_value";
			//instance.put(key, value);
			
			System.out.println(instance.delete(key));
			
			System.out.println(instance.get(key));
		} catch (Exception e) {
			log.error("testMemcachedDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedGet() {
		try {
			key = "a";
			value = "a_value";
			//instance.put(key, value);
			
			System.out.println(instance.get(key));
			
		} catch (Exception e) {
			log.error("testMemcachedGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedExpire() {
		try {
			key = "a";
			value = "a_value";
			Date expireDt = DateTimeUtil.parseDateTime("2015-02-10 17:11:15");
			instance.put(key, value, expireDt);
			
			System.out.println(instance.get(key));
			
			// 通过线程休眠 延迟运行时间，模拟 定时任务
			Thread.sleep(60 * 1000);
			
			System.out.println(instance.get(key));
			
		} catch (Exception e) {
			log.error("testMemcachedExpire =====> ", e);
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
