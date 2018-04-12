/**
 * 描述: 
 * HashTest.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * HashTest
 */
public final class HashTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHash() {
		try {
			
			
		} catch (Exception e) {
			log.error("testHash =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHDel() {
		try {
			/*
			 * 删除哈希表中1/多个字段
			 */
			String key = "COM:HASH:" + "PERSON";
			String[] fields = {"name", "age"};
			// 0-失败，n-成功
			Long result = jedis.hdel(key, fields);
			log.info("testHDel =====> result = " + result);
		} catch (Exception e) {
			log.error("testHDel =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHExists() {
		try {
			/*
			 * 检查哈希表key中，给定域的field是否存在
			 */
			String key = "COM:HASH:" + "PERSON";
			String field = "sex";
			Boolean flag = jedis.hexists(key, field);
			log.info("testHExists =====> flag = " + flag);
		} catch (Exception e) {
			log.error("testHExists =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHGet() {
		try {
			String key = "COM:HASH:" + "PERSON";
			String field = "sex";
			String value = jedis.hget(key, field);
			log.info("testHGet =====> value = " + value);
		} catch (Exception e) {
			log.error("testHGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHGetAll() {
		try {
			String key = "COM:HASH:" + "PERSON";
			Map<String, String> mapValue = jedis.hgetAll(key);
			log.info("testHGetAll =====> code = " + mapValue.get("code"));
			log.info("testHGetAll =====> sex = " + mapValue.get("sex"));
			
		} catch (Exception e) {
			log.error("testHGetAll =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHIncrBy() {
		try {
			String key = "COM:HASH:" + "PERSON";
			Long value = jedis.hincrBy(key, "age", 10L);
			log.info("testHIncrBy =====> value = " + value);
		} catch (Exception e) {
			log.error("testHIncrBy =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHIncrByFloat() {
		try {
			String key = "COM:HASH:" + "PERSON";
			Double value = jedis.hincrByFloat(key, "age", 10.0);
			log.info("testHIncrByFloat =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testHIncrByFloat =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHKeys() {
		try {
			String key = "COM:HASH:" + "PERSON";
			Set<String> keys = jedis.hkeys(key);
			for (String value : keys)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testHKeys =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHLen() {
		try {
			String key = "COM:HASH:" + "PERSON";
			Long length = jedis.hlen(key);
			
			log.info("testHLen =====> length = " + length);
			 
		} catch (Exception e) {
			log.error("testHLen =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHMGet() {
		try {
			String key = "COM:HASH:" + "PERSON";
			String[] fields = {"name" ,"age"};
			List<String> values = jedis.hmget(key, fields);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testHMGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHMSet() {
		try {
			String key = "COM:HASH:" + "PERSON";
			String[] fields = {"name" ,"age"};
			Map<String, String> hash = new HashMap<String, String>();
			hash.put("nickname", "哈哈");
			String result = jedis.hmset(key, hash);
			log.info("testHMSet =====> result = " + result);
		} catch (Exception e) {
			log.error("testHMSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHSet() {
		try {
			/*
			 * 将哈希表key中的域的值设置为value
			 * 若key不存在，则一个新的哈希表被创建并执行hset操作
			 * 若field已经存在于哈希表中，旧值将被覆盖
			 */
			String key = "COM:HASH:" + "PERSON";
			String field = null;
			// 0-失败，1-成功
			Long result = 0L;
			
			field = "name";
			result = jedis.hset(key, field, "张三");
			log.info("testHSet =====> result = " + result);
			
			field = "age";
			result = jedis.hset(key, field, "25");
			log.info("testHSet =====> result = " + result);
			
			field = "sex";
			result = jedis.hset(key, field, "male");
			log.info("testHSet =====> result = " + result);
			
			field = "address";
			result = jedis.hset(key, field, "广东广州市");
			log.info("testHSet =====> result = " + result);
			
			field = "code";
			result = jedis.hset(key, field, "020");
			log.info("testHSet =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testHSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHSetNX() {
		try {
			String key = "COM:HASH:" + "PERSON";
			String field = null;
			// 0-失败，1-成功
			Long result = 0L;
			
			field = "name";
			// 不存在才设置值
			result = jedis.hsetnx(key, field, "new name");
			log.info("testHSetNX =====> result = " + result);
		} catch (Exception e) {
			log.error("testHSetNX =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHVals() {
		try {
			String key = "COM:HASH:" + "PERSON";
			List<String> values = jedis.hvals(key);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testHVals =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHScan() {
		try {
			
			
		} catch (Exception e) {
			log.error("testHScan =====> ", e);
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
