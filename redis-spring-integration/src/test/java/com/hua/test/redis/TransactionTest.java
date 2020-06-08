/**
 * 描述: 
 * TransactionTest.java
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

import redis.clients.jedis.Transaction;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * TransactionTest
 */
public final class TransactionTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDiscard() {
		try {
			/*
			 * 取消事务，放弃执行事务块内的所有命令
			 * 用watch监控某些key，可以用unwatch取消监控
			 */
			//String key = "COM:TX:V1";
			// 取消事务
			jedis.disconnect();
			
		} catch (Exception e) {
			log.error("testDiscard =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExec() {
		try {
			/*
			 * 执行事务，执行事务块内的所有命令
			 * 用watch监控某些key，可以用unwatch取消监控
			 */
			//String key = "COM:TX:V1";
			// 事务块开始
			Transaction transaction = jedis.multi();
			// 执行事务
			List<Object> objs = transaction.exec();
			
			} catch (Exception e) {
			log.error("testExec =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMulti() {
		try {
			/*
			 * 标记一个事务块开始
			 * 用watch监控某些key，可以用unwatch取消监控
			 */
			//String key = "COM:TX:V1";
			// 事务块开始
			Transaction transaction = jedis.multi();
			// 通过此对象，可以执行各种操作和jedis对象一样
		} catch (Exception e) {
			log.error("testMulti =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUnWatch() {
		try {
			/*
			 * 标记一个事务块开始
			 * 用watch监控某些key，可以用unwatch取消监控
			 */
			//String key = "COM:TX:V1";
			// 事务块开始
			Transaction transaction = jedis.multi();
			// 取消监控
			jedis.unwatch();
		} catch (Exception e) {
			log.error("testUnWatch =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWatch() {
		try {
			/*
			 * 标记一个事务块开始
			 * 用watch监控某些key，可以用unwatch取消监控
			 */
			//String key = "COM:TX:V1";
			// 事务块开始
			Transaction transaction = jedis.multi();
			String[] keys = {"key1", "key2"};
			// 监控某些key
			transaction.watch(keys);
		} catch (Exception e) {
			log.error("testWatch =====> ", e);
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
