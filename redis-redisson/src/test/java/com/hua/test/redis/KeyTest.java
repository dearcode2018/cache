/**
 * 描述: 
 * KeyTest.java
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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.SetParams;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * KeyTest
 */
public final class KeyTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testKey() {
		try {
			
			
		} catch (Exception e) {
			log.error("testKey =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAdd() {
		try {
			// 
			User user = new User();
			user.setId("20151231");
			user.setUsername("世界之窗");
			user.setLastLoginIp("192.168.6.38");
			user.setNickname("绵羊");
			user.setPassword("123456");
			user.setValid(true);
			user.setLastLoginTime(new Date());
			// 
			String value = JacksonUtil.writeAsString(user);
			String key = "COM:USER:2015:" + user.getId();
			/*
			 * nxxx: NX|XX (NX: none exist，XX: exist) 
			 * NX: 不存在的时候才设置值
			 * XX: 存在时才设置值
			 */
			String nxx = "NX";
			//nxx = "XX";
			// expx expire time units: EX = seconds; PX = milliseconds
			String expx = "EX";
			int time = 10000;
			SetParams param = new SetParams();
			param.nx().ex(time);
			
			String statusCode = jedis.set(key.getBytes(), value.getBytes(), param);
			log.info("testAdd =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testAdd =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 删除一个或多个key，返回被删除的key的数量
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDel1() {
		try {
			String key = "COM:USER:2015:" + "20151229";
			Long count = jedis.del(key);
			
			log.info("testDel1 =====> count = " + count);
			
		} catch (Exception e) {
			log.error("testDel1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 删除一个或多个key，返回被删除的key的数量
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDel2() {
		try {
			String[] keys = {"COM:USER:2015:" + "20151229", "COM:USER:2015:" + "20151230"};
			Long count = jedis.del(keys);
			log.info("testDel2 =====> count = " + count);
		} catch (Exception e) {
			log.error("testDel2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 删除一个或多个key，返回被删除的key的数量
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDel3() {
		try {
			byte[] key1 = ("COM:USER:2015:" + "20151229").getBytes();
			byte[] key2 = ("COM:USER:2015:" + "20151230").getBytes();
			// key 是字节形式
			Long count = jedis.del(key1, key2);
			log.info("testDel3 =====> count = " + count);
		} catch (Exception e) {
			log.error("testDel3 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 序列化给定的key，并返回被序列化的值
	 * 若key不存在，则返回null
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDump() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			byte[] bt = jedis.dump(key);
			
			System.out.println(Arrays.toString(bt));
			
			//bt = jedis.dump(bt);
			//System.out.println(Arrays.toString(bt));
		} catch (Exception e) {
			log.error("testDump =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 检查给定的key是否存在
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExists() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			key = "COM:USER:2015:" + "20151232";
			boolean flag = jedis.exists(key);
			log.info("testExists =====> flag = " + flag);
			
 		} catch (Exception e) {
			log.error("testExists =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 设置生存时间，单位: 秒
	 * 设置为0 会自动删除
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExpire() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			int seconds = 10;
			// 1-成功，0-失败
			Long result = jedis.expire(key, seconds);
			log.info("testExpire =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testExpire =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 设置失效时间，格式: Unix时间戳
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExpireAt() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 2);
			long unixTime = cal.getTimeInMillis() / 1000;
			
			// 1-成功，0-失败
			Long result =jedis.expireAt(key, unixTime);
			log.info("testExpire =====> result = " + result);
		} catch (Exception e) {
			log.error("testExpireAt =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 查找所有符合给定模式pattern的key
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testKeys() {
		try {
			/*
			 * * :  多个字符
			 *  ? : 单个字符
			 *  [0-9] : 可选单个字符
			 */
			String pattern = "COM:USER:2015:*";
			//pattern = "COM:USER:2015:?";
			pattern = "COM:USER:2015:2015123[2-9]";
			Set<String> keys = jedis.keys(pattern);
			
			log.info("testKeys =====> size = " + keys.size());
			
		} catch (Exception e) {
			log.error("testKeys =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 迁移，将key原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key会出现在目标实例上，而当前实例上的key会被删除
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMigrate() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			/*
			 *  迁到当前实例的另外一个库 会抛异常 : Read timed out
			 *  该方法是在2个实例之间使用
			 */
			String result = jedis.migrate("127.0.0.1", 6379, key, 1, 10000);
			log.info("testMigrate =====> result = " + result);
		} catch (Exception e) {
			log.error("testMigrate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 在一个实例中从一个库迁移到另外一个库
	 * 一旦传送成功，key会出现在目标实例上，而当前实例上的key会被删除
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMove() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			// 移动成功返回 1 ，失败则返回 0 
			Long result = jedis.move( key, 2);
			log.info("testMigrate =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testMove =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testObject() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			
			String encode = jedis.objectEncoding(key);
			
			log.info("testObject =====> encode = " + encode);
			
		} catch (Exception e) {
			log.error("testObject =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 持久化
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPersist() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			
			// 成功返回 1 ，失败则返回 0 
			Long result =jedis.persist(key);
			
			log.info("testPersist =====> result = " + result);
		} catch (Exception e) {
			log.error("testPersist =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPExpire() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			long milliseconds = 3000;
			// 1-成功，0-失败
			Long result = jedis.pexpire(key, milliseconds);
			log.info("testExpire =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testPExpire =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 以毫秒为单位
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPExpireAt() {
		try {
			String key = "COM:USER:2015:" + "20151231";
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 2);
			long milliseconds = cal.getTimeInMillis();
			
			// 1-成功，0-失败
			Long result =jedis.pexpireAt(key, milliseconds);
			log.info("testPExpireAt =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testPExpireAt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 返回指定key的剩余生存时间，单位: 毫秒
	 * 特殊返回值: 当key不存在，返回-2，当key存在但没有设置生存时间，返回-1
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPTTL() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			// 
			long result = jedis.pttl(key);
			log.info("testPTTL =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testPTTL =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 从当前库中，随机返回一个key（不删除）
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRandomKey() {
		try {
			String key = jedis.randomKey();
			log.info("testRandomKey =====> key = " + key);
			
		} catch (Exception e) {
			log.error("testRandomKey =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 重命名，新的key已经存在则做替换
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRename() {
		try {
			String key = "COM:USER:2015:" + "20151225";
			String newKey = "COM:USER:2015:" + "20151230";
			
			String statusCode = jedis.rename(key, newKey);
			log.info("testRename =====> statusCode = " + statusCode);
		} catch (Exception e) {
			log.error("testRename =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 如果新的key已经存在，则命名失败
	 * 前提是没有
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRenameNX() {
		try {
			String key = "COM:USER:2015:" + "20151225";
			String newKey = "COM:USER:2015:" + "20151230";
			// 1-成功，0-失败(该key以及已存在)
			Long result = jedis.renamenx(key, newKey);
			log.info("testRenameX =====> result = " + result);
		} catch (Exception e) {
			log.error("testRenameX =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRestore() {
		try {
			String key = "COM:USER:2015:" + "20151225";
			int ttl = 0;
			byte[] serializedValue = "aa".getBytes();
			
			String statusCode = jedis.restore(key, ttl, serializedValue);
			log.info("testRename =====> statusCode = " + statusCode);
			
		} catch (Exception e) {
			log.error("testRestore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 数值排序，集合/数组
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSort() {
		try {
			String key = "COM:NUMBER";
			SortingParams sortingParameters = new SortingParams();
			sortingParameters.desc();
			List<String> values = jedis.sort(key, sortingParameters);
			//List<String> values = jedis.sort(key);
			log.info("testSort =====> size = " + values.size());
			for (String value : values)
			{
				System.out.print(value + ",");
			}
			System.out.println();
			
		} catch (Exception e) {
			log.error("testSort =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 返回指定key的剩余生存时间，单位: 秒
	 * 特殊返回值: 当key不存在，返回-2，当key存在但没有设置生存时间，返回-1
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTTL() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			// 
			long result = jedis.ttl(key);
			log.info("testTTL =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testTTL =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testType() {
		try {
			String key = "COM:USER:2015:" + "20151230";
			String result = jedis.type(key);
			
			log.info("testType =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testType =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testScan() {
		try {
			//jedis.scan(0);
			
		} catch (Exception e) {
			log.error("testScan =====> ", e);
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
