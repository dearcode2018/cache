/**
 * 描述: 
 * StringTest.java
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

import redis.clients.jedis.BitOP;
import redis.clients.jedis.params.SetParams;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * StringTest
 */
public final class StringTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAppend() {
		try {
			/*
			 * 如果key存在并且是一个字符串，append将value追加到key原来值的末尾
			 * 如果key不存在，append就简单地给key设为value1
			 */
			String key = "COM:USER:2015:" + "12:123";
			String value = "add";
			Long result = jedis.append(key, value);
			log.info("testAppend =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testAppend =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBitCount() {
		try {
			/*
			 * 统计比特为1的数量
			 */
			String key = "COM:BIT:" + "H1";
			
			Long count = jedis.bitcount(key);
			log.info("testBitCount =====> count = " + count);
			
		} catch (Exception e) {
			log.error("testBitCount =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 位操作
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBitOp() {
		try {
			/*
			 * 对多个key进行位操作，结果保存保存到destKey中
			 * AND OR XOR NOT
			 */
			String key = "COM:BIT:" + "H1";
			String destKey = "COM:BIT:" + "H2";
			Long result = jedis.bitop(BitOP.AND, destKey, key);
			//jedis.bitop(BitOP.OR, destKey, key);
			//jedis.bitop(BitOP.XOR, destKey, key);
			//jedis.bitop(BitOP.NOT, destKey, key);
			log.info("testBitOp =====> result = " + result);
		} catch (Exception e) {
			log.error("testBitOp =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecr() {
		try {
			/*
			 * 将key中存储的数字值减1
			 * 若key不存在，那么key的值会先被初始化为0 然后再执行DECR操作
			 * 返回值，执行decr命令之后key的值
			 */
			String key = "COM:VALUE:" + "D";
			Long value = jedis.decr(key);
			log.info("testDecr =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testDecr =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecrBy() {
		try {
			/*
			 * 将key中存储的数字值减decrement 
			 * 若key不存在，那么key的值会先被初始化为0 然后再执行DECR操作
			 * 返回值，执行decr命令之后key的值
			 */
			String key = "COM:VALUE:" + "DBy";
			long decrement = 20;
			Long value = jedis.decrBy(key, decrement);
			log.info("testDecr =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testDecrBy =====> ", e);
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
			String key = "COM:VALUE:" + "D";
			String value = jedis.get(key);
			log.info("testGet =====> value = " + value);
			
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
	public void testGetBit() {
		try {
			String key = "COM:BIT:H1";
			Long offset = 10L;
			Boolean value = jedis.getbit(key, offset);
			log.info("testGet =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testGetBit =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetRange() {
		try {
			String key = "COM:USER:2015:" + "12:123";
			long startOffset = 0L;
			// 包括
			long endOffset = 5L;
			String value = jedis.getrange(key, startOffset, endOffset);
			log.info("testGet =====> value = " + value);
		} catch (Exception e) {
			log.error("testGetRange =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetSet() {
		try {
			String key = "COM:VALUE:" + "SOME";
			String newValue = "newValue ----";
			String oldValue = jedis.getSet(key, newValue);
			log.info("testGetSet =====> oldValue = " + oldValue);
		} catch (Exception e) {
			log.error("testGetSet =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIncr() {
		try {
			/*
			 * 将key中存储的数字值加1
			 * 若key不存在，那么key的值会先被初始化为0 然后再执行INCR操作
			 * 返回值，执行INCR命令之后key的值
			 */
			String key = "COM:VALUE:" + "I";
			Long value = jedis.incr(key);
			log.info("testIncr =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testIncr =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIncrBy() {
		try {
			/*
			 * 将key中存储的数字值减decrement 
			 * 若key不存在，那么key的值会先被初始化为0 然后再执行DECR操作
			 * 返回值，执行decr命令之后key的值
			 */
			String key = "COM:VALUE:" + "IBy";
			long increment = 20;
			Long value = jedis.incrBy(key, increment);
			log.info("testIncrBy =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testIncrBy =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIncrByFoat() {
		try {
			/*
			 * 将key中存储的数字值减decrement 
			 * 若key不存在，那么key的值会先被初始化为0 然后再执行DECR操作
			 * 返回值，执行decr命令之后key的值
			 */
			String key = "COM:VALUE:" + "IByF";
			double increment = 20.0;
			double value = jedis.incrByFloat(key, increment);
			log.info("testIncrByFoat =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testIncrByFoat =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMGet() {
		try {
			/**
			 * 返回一个/多个给定key的值
			 */
			String key1 = "COM:VALUE:" + "IByF";
			String key2 = "COM:VALUE:" + "IBy";
			String[] keys = {key1, key2};
			List<String> values = jedis.mget(keys);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testMGet =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMSet() {
		try {
			/*
			 * 设置多个key value，在数组中依次出现
			 */
			String[] keysValues = {"COM:key1", "value1", "COM:key2", "value2", "COM:key3", "value3"};
			String result = jedis.mset(keysValues);
			log.info("testMSet =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testMSet =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMSetNX() {
		try {
			/*
			 * 设置多个key value，在数组中依次出现
			 * 不存在才设置
			 */
			String[] keysValues = {"COM:key1", "value1", "COM:key2", "value2", "COM:key3", "value3"};
			// 0-失败，1-成功
			Long result = jedis.msetnx(keysValues);
			log.info("testMSet =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testMSetNX =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPSetEX() {
		try {
			
			
		} catch (Exception e) {
			log.error("testPSetEX =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSet() {
		try {
			String key = "COM:VALUE:" + "SOME";
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			String nxx = "NX";
			// expx expire time units: EX = seconds; PX = milliseconds
			String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			String value = jedis.set(key, "hahha", param);
			log.info("testGet =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testSet =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSetBit() {
		try {
			String key = "COM:BIT:" + "H1";
			long offset = 10;
			Boolean flag = jedis.setbit(key, offset, "1");
			log.info("testSetBit =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testSetBit =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSetEX() {
		try {
			String key = "COM:VALUE:" + "SOME";
			// expx expire time units: EX = seconds; PX = milliseconds
			String expx = "EX";
			int seconds = 5000;
			String value = "haha";
			String result = jedis.setex(key, seconds, value);
			log.info("testGet =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testSetEX =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSetNX() {
		try {
			String key = "COM:VALUE:" + "NX";
			String value = "abcde";
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			Long result = jedis.setnx(key, value);
			log.info("testGet =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testSetNX =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSetRange() {
		try {
			String key = "COM:VALUE:" + "SOME";
			String value = "ddd";
			/*
			 * 从指定下标开始替换为value指定的值
			 */
			Long result = jedis.setrange(key, 2, value);
			log.info("testGet =====> result = " + result);
		} catch (Exception e) {
			log.error("testSetRange =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testStrLen() {
		try {
			String key = "COM:VALUE:" + "SOME";
			Long length = jedis.strlen(key);
			log.info("testStrLen =====> length = " + length);
		} catch (Exception e) {
			log.error("testStrLen =====> ", e);
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
