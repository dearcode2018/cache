/**
 * 描述: 
 * SetTest.java
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

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SetTest
 */
public final class SetTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSet() {
		try {
			
			
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
	public void testSAdd() {
		try {
			/*
			 * 添加一个或多个值到集合中
			 */
			String key = "COM:SET:USERS";
			int i = 3;
			// 1-成功，0-失败
			Long result = null;
			User user = null;
			String value = null;
			user = new User();
			user.setId("20151228" + (i++));
			user.setUsername("世界之窗1");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊1");
			user.setPassword("123456");
			user.setValid(true);
			// 
			value = JacksonUtil.writeAsString(user);
			result =	jedis.sadd(key, value);

			log.info("testSAdd =====> result = " + result);
		} catch (Exception e) {
			log.error("testSAdd =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSCard() {
		try {
			/*
			 * 返回集合key的基数(集合中元素的数量)
			 */
			String key = "COM:SET:USERS";
			Long length = jedis.scard(key);
			log.info("testSCard =====> length = " + length);
		} catch (Exception e) {
			log.error("testSCard =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSDiff() {
		try {
			/*
			 * 返回多个集合之间的差集
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			Set<String> values = jedis.sdiff(keys);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testSDiff =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSDiffStore() {
		try {
			/*
			 * 返回多个集合之间的差集，且将结果保存到目的集合
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			String dstKey = "COM:SET:USERS-DEST";
			Long result = jedis.sdiffstore(dstKey, keys);
			log.info("testSDiffStore =====> result = " + result);
		} catch (Exception e) {
			log.error("testSDiffStore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSInter() {
		try {
			/*
			 * 返回多个集合之间的交集，且将结果保存到目的集合
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			Set<String> values = jedis.sinter(keys);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testSInter =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSInterStore() {
		try {
			/*
			 * 返回多个集合之间的交集，且将结果保存到目的集合
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			String dstKey = "COM:SET:USERS-DEST";
			Long result = jedis.sinterstore(dstKey, keys);
			log.info("testSDiffStore =====> result = " + result);
		} catch (Exception e) {
			log.error("testSInterStore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSIsMember() {
		try {
			/*
			 * 是否是该集合的成员
			 */
			String key = "COM:SET:USERS";
			String member = "haha";
			boolean flag = jedis.sismember(key, member);
			log.info("testSIsMember =====> flag = " + flag);
		} catch (Exception e) {
			log.error("testSIsMember =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSMembers() {
		try {
			String key = "COM:SET:USERS";
			Set<String> values = jedis.smembers(key);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testSMembers =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSMove() {
		try {
			/*
			 * 将一个元素从一个集合移动到另外一个集合
			 */
			String sourceKey = "COM:SET:USERS";
			String key = "COM:SET:USERS1";
			String member = "haha";
			Long result = jedis.smove(sourceKey, key, member);
			log.info("testSMove =====> result = " + result);
		} catch (Exception e) {
			log.error("testSMove =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSPop() {
		try {
			String key = "COM:SET:USERS";
			String value = jedis.spop(key);
			System.out.println(value);
		} catch (Exception e) {
			log.error("testSPop =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSRandMember() {
		try {
			/*
			 * 随机取出其中一个元素
			 */
			String key = "COM:SET:USERS";
			String value = jedis.srandmember(key);
			System.out.println(value);
			
		} catch (Exception e) {
			log.error("testSRandMember =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSRem() {
		try {
			/*
			 * 移除指定多个元素
			 */
			String key = "COM:SET:USERS";
			String[] members = {"a", "b"};
			Long result = jedis.srem(key, members);
			log.info("testSRem =====> result = " + result);
		} catch (Exception e) {
			log.error("testSRem =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSUnion() {
		try {
			/*
			 * 返回多个集合之间的并集
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			Set<String> values = jedis.sunion(keys);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testSUnion =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSUnionStore() {
		try {
			/*
			 * 返回多个集合之间的交集，且将结果保存到目的集合
			 */
			String[] keys = {"COM:SET:USERS", "COM:SET:USERS1"};
			String dstKey = "COM:SET:USERS-DEST";
			Long result = jedis.sunionstore(dstKey, keys);
			log.info("testSUnionStore =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testSUnionStore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSScan() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSScan =====> ", e);
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
