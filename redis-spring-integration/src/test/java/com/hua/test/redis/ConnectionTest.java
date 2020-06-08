/**
 * 描述: 
 * ConnectionTest.java
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

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConnectionTest
 */
public final class ConnectionTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAuth() {
		try {
			/*
			 * 通过设置配置文件中 requirepass 项的值(使用命令 
			 * CONFIG SET requirepass password )，
			 * 可以使用密码来保护 Redis 服务器。
			 * 如果开启了密码保护的话，在每次连接 Redis 服务器之后，
			 * 就要使用 AUTH 命令解锁，解锁之后才能使用其他 Redis 命令。
			 * 如果 AUTH 命令给定的密码 password 和配置文件中的密码相符的话，
			 * 服务器会返回 OK 并开始接受命令输入。
			 * 另一方面，假如密码不匹配的话，服务器将返回一个错误，
			 * 并要求客户端需重新输入密码。
			 * 
			 * config set requirpass 设置密码只是暂时的，redis重启之后密码便失效，
			 * 手工修改配置文件才能永久生效
			 */
			String password = null;
			password = "redis";
			// redis服务器设置了密码，且密码正确 则返回OK
			String result = jedis.auth(password);
			log.info("testAuth =====> result = " + result);
		} catch (Exception e) {
			log.error("testAuth =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEcho() {
		try {
			/*
			 * 答应一个特定的信息，测试时使用
			 * 返回就是输入信息
			 */
			String result =jedis.echo("haha");
			log.info("testAuth =====> result = " + result);
		} catch (Exception e) {
			log.error("testEcho =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPing() {
		try {
			/*
			 * 使用客户端向redis服务器发送一个PING，若服务器
			 * 运行正常的话，会返回一个PONG
			 */
			// PONG
			String result =jedis.ping();
			log.info("testPing =====> result = " + result);
		} catch (Exception e) {
			log.error("testPing =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testQuit() {
		try {
			// OK
			String result =jedis.quit();
			log.info("testQuit =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testQuit =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSelect() {
		try {
			/*
			 * 切换指定的数据库，数据库索引index
			 * 用数字指定，从0开始，默认使用0
			 */
			int index = 1;
			String result =jedis.select(index);
			
			log.info("testSelect =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testSelect =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBuildSet() {
		try {
			
			/*
			 * 切换指定的数据库，数据库索引index
			 * 用数字指定，从0开始，默认使用0
			 */
			int index = 2;
			String resultCode =jedis.select(index);
			
			log.info("testBuildSet =====> resultCode = " + resultCode);
			// 将一个或多个值插入表头
			User user = null;
			String key = "COM:SET:USERS";
			String key1 = "COM:SET:USERS1";
			String key2 = "COM:SET:USERS2";
			String value = null;
			int i = 0;
			// 1-成功，0-失败
			Long result = null;
			
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
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
			
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
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
			
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
			result =	jedis.sadd(key1, value);
			result =	jedis.sadd(key2, value);
			log.info("testBuildSet =====> size = " + result);
		} catch (Exception e) {
			log.error("testBuildSet =====> ", e);
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
