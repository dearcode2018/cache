/**
 * 描述: 
 * SortedSetTest.java
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

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SortedSetTest
 */
public final class SortedSetTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZAdd() {
		try {
			/*
			 * 将一个或多个元素放入有序集合key中
			 * 若元素已经存在，则更新score，并重新插入
			 * 这个元素，来保证该元素在集合中的正确位置
			 * 若score相同，则按照字符串来排序
			 */
			String key = "COM:SORTEDSET:USERS";
			double score  = 1.2;
			String member = "d";
			jedis.zadd(key, score, member);
			
		} catch (Exception e) {
			log.error("testZAdd =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZCard() {
		try {
			/*
			 * 长度
			 */
			String key = "COM:SORTEDSET:USERS";
			Long length = jedis.zcard(key);
			log.info("testZCard =====> length = " + length);
		} catch (Exception e) {
			log.error("testZCard =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZCount() {
		try {
			/*
			 * 
			 */
			String key = "COM:SORTEDSET:USERS";
			double min = 1.1;
			double max = 2.0;
			Long count = jedis.zcount(key, min, max);
			log.info("testZCount =====> count = " + count);
		} catch (Exception e) {
			log.error("testZCount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZIncrBy() {
		try {
			String key = "COM:SORTEDSET:USERS";
			String member = "b";
			double score = 3.1;
			jedis.zincrby(key, score, member);
			
		} catch (Exception e) {
			log.error("testZIncrBy =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRange() {
		try {
			String key = "COM:SORTEDSET:USERS";
			String member = "b";
			double score = 3.1;
			Long start = 1L;
			// 包括
			Long end = 1L;
			Set<String> values = jedis.zrange(key, start, end);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testZRange =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRangeByScore() {
		try {
			String key = "COM:SORTEDSET:USERS";
			double min = 1.1;
			double max = 2.0;
			Set<String> values = jedis.zrangeByScore(key, min, max);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testZRangeByScore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRank() {
		try {
			/*
			 * 获取有序集合中指定成员的排名
			 * score值最小的成员排名为0
			 * 0 1 2
			 */
			String key = "COM:SORTEDSET:USERS";
			String member = "b";
			Long rank = jedis.zrank(key, member);
			log.info("testZRank =====> rank = " + rank);
			
		} catch (Exception e) {
			log.error("testZRank =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRem() {
		try {
			/*
			 * 移除有序集合中一个或多个成员，
			 * 不存在的成员被忽略
			 */
			String key = "COM:SORTEDSET:USERS";
			String[] members = {"a", "d", "ff"};
			Long result = jedis.zrem(key, members);
			log.info("testZRem =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testZRem =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRemRangeByRank() {
		try {
			/*
			 * 移除有序集合	中指定排名区间内的所有成员
			 */
			String key = "COM:SORTEDSET:USERS";
			// 0-表示第一个成员
			Long start = 2L;
			// 包括
			Long end = 5L;
			Long result = jedis.zremrangeByRank(key, start, end);
			log.info("testZRemRangeByRank =====> result = " + result);
		} catch (Exception e) {
			log.error("testZRemRangeByRank =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRemRangeByScore() {
		try {
			/*
			 * 移除有序集合	中指定score区间内的所有成员
			 */
			String key = "COM:SORTEDSET:USERS";
			double min = 1.1;
			double max = 6.1;
			Long result = jedis.zremrangeByScore(key, min, max);
			log.info("testZRemRangeByScore =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testZRemRangeByScore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRevRange() {
		try {
			/**
			 *
			 * 返回有序集合中指定的递减指定范围的排名
			 * 最后一个值下标是 0
			 */
			String key = "COM:SORTEDSET:USERS";
			Long start = 1L;
			// 包括
			Long end = 3L;
			Set<String> values = jedis.zrevrange(key, start, end);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testZRevRange =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRevRangeByScore() {
		try {
			/**
			 *
			 * 返回有序集合中指定的递减指定范围的分数
			 * 最后一个值分数最大
			 */
			String key = "COM:SORTEDSET:USERS";
			double min = 11;
			double max = 40.5;
			Set<String> values = jedis.zrevrangeByScore(key, min, max);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testZRevRangeByScore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRevRank() {
		try {
			/*
			 * 获取有序集合中指定成员倒序的排名
			 * score值最大的成员排名为0
			 * 0 1 2
			 */
			String key = "COM:SORTEDSET:USERS";
			String member = "X";
			Long rank = jedis.zrevrank(key, member);
			log.info("testZRevRank =====> rank = " + rank);
			
		} catch (Exception e) {
			log.error("testZRevRank =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZScore() {
		try {
			/*
			 * 获取有序集合中指定成员倒序的score值
			 */
			String key = "COM:SORTEDSET:USERS";
			String member = "X";
			double score = jedis.zscore(key, member);
			log.info("testZScore =====> score = " + score);
			
		} catch (Exception e) {
			log.error("testZScore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZUnionStore() {
		try {
			/*
			 * 获取多个有序集合的并集
			 */
			String destKey = "COM:SORTEDSET:USERS-DEST";
			String key1 = "COM:SORTEDSET:USERS1";
			String key2 = "COM:SORTEDSET:USERS2";
			String[] sets = {key1, key2};
			Long result = jedis.zunionstore(destKey, sets);
			log.info("testZUnionStore =====> result = " + result);
		} catch (Exception e) {
			log.error("testZUnionStore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZInterStore() {
		try {
			/*
			 * 获取多个有序集合的交集
			 */
			String destKey = "COM:SORTEDSET:USERS-DEST2";
			String key1 = "COM:SORTEDSET:USERS1";
			String key2 = "COM:SORTEDSET:USERS2";
			String[] sets = {key1, key2};
			Long result = jedis.zinterstore(destKey, sets);
			log.info("testZInterStore =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testZInterStore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZScan() {
		try {
			
		} catch (Exception e) {
			log.error("testZScan =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRangeByLEX() {
		try {
			/*
			 * 当有序集合的所有成员具有相同的score值时，
			 * 有序集合的元素会根据成员的字典顺序(lexicographical ordering)
			 * 来进行排序
			 * 根据union编码的顺序
			 */
			String key = "COM:SORTEDSET:USERS";
			/*
			 * [ 大/小等于
			 * ( 大/小于
			 */
			String min = "[a";
			min = "(a";
			String max = "[f";
			max = "(f";
			Set<String> values = jedis.zrangeByLex(key, min, max);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testZRangeByLEX =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZLEXCount() {
		try {
			/*
			 * 统计 根据字典排序 处于min/max范围内的元素的数量
			 */
			String key = "COM:SORTEDSET:USERS";
			/*
			 * [ 大/小等于
			 * ( 大/小于
			 */
			String min = "[a";
			min = "(a";
			String max = "[f";
			max = "(f";
			Long count = jedis.zlexcount(key, min, max);
			System.out.println(count);
			
		} catch (Exception e) {
			log.error("testZLEXCount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testZRemRangeByLEX() {
		try {
			/*
			 * 移除有序集合	中指定 字典顺序区间内的所有成员
			 */
			String key = "COM:SORTEDSET:USERS";
			/*
			 * [ 大/小等于
			 * ( 大/小于
			 */
			String min = "[a";
			min = "(a";
			String max = "[f";
			Long result = jedis.zremrangeByLex(key, min, max);
			log.info("testZRemRangeByLEX =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testZRemRangeByLEX =====> ", e);
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
