/**
 * 描述: 
 * EhcacheTest.java
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

import org.junit.Ignore;
import org.junit.Test;

import com.hua.cache.EhcacheService;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * EhcacheTest
 */
public final class EhcacheTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessExpire() {
		try {
			
			String key = "a_test_001";
			EhcacheService.put(key, "12344556");
			
			log.info("testExpire =====> " + EhcacheService.contain(key));
			/*
			 * 调用contain方法并不会刷新缓存时间
			 */
			while (EhcacheService.contain(key))
			{
				Thread.sleep(30 * 1000);
				log.info("testExpire =====> ");
			}
			log.info("testExpire =====> " + EhcacheService.contain(key));
			
		} catch (Exception e) {
			log.error("testExpire =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessExpire2() {
		try {
			
			String key = "a_test_001";
			EhcacheService.put(key, "12344556");
			
			log.info("testExpire =====> " + EhcacheService.contain(key));
			
			while (null != EhcacheService.get(key))
			{
				Thread.sleep(30 * 1000);
				log.info("testExpire =====> ");
			}
			log.info("testExpire =====> " + EhcacheService.contain(key));
			
		} catch (Exception e) {
			log.error("testAccessExpire2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExpire() {
		try {
			
			String key = "a_test_001";
			EhcacheService.put(key, "12344556");
			
			log.info("testExpire =====> " + EhcacheService.contain(key));
			
			// 断点
			log.info("testExpire =====> ");
			
			log.info("testExpire =====> " + EhcacheService.contain(key));
			
		} catch (Exception e) {
			log.error("testExpire =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEhcacheService() {
		try {
			
			EhcacheService.put("year", "2018");
			
			
			log.info("testEhcacheService =====> " + EhcacheService.get("year"));
			
		} catch (Exception e) {
			log.error("tesEhcacheServicet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetProperties() {
		try {
			System.out.println(System.getProperty("java.io.tmpdir"));
		} catch (Exception e) {
			log.error("testGetProperties =====> ", e);
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
