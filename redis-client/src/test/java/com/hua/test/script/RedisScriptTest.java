/**
 * 描述: 
 * RedisScriptTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.script;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hua.ApplicationStarter;
import com.hua.test.BaseTest;

import redis.clients.jedis.Jedis;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RedisScriptTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class RedisScriptTest extends BaseTest {

	
	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@ExtendWith(SpringExtension.class)
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@ExtendWith(SpringExtension.class)
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	//@Resource
	//private UserController userController;
	private Jedis client;
	
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testScript() {
		try {
			String script = "local key = KEYS[1];\n" + 
					"local value = ARGV[1];\n" + 
					"local timeout = ARGV[2];\n" + 
					"redis.call('SETNX', key, value);\n" + 
					"redis.call('EXPIRE', key, timeout);\n" +
					"local result = redis.call('GET', key);\n" + 
					"return result;";
			script = "return {redis.call('GET', KEYS[1]), redis.call('GET', KEYS[2]), ARGV[1], ARGV[2]}";
			String[] keys = {"a:b", "a:c"};
			String[] args = {"lettuce script2", "20"};
			args = new String[] {"args11", "args22"};
			String[] params = {keys[0], keys[1], args[0], args[1]};
			
			Object result = client.eval(script, 2, params);
			
			System.out.println(result);
			
			
		} catch (Exception e) {
			log.error("testScript =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testEval() {
		try {
			String script = "local key = KEYS[1];\n" + 
					"local value = ARGV[1];\n" + 
					"local timeout = ARGV[2];\n" + 
					"redis.call('SETNX', key, value);\n" + 
					"redis.call('EXPIRE', key, timeout);\n" +
					"local result = redis.call('GET', key);\n" + 
					"return result;";
			script = "return {redis.call('GET', KEYS[1]), redis.call('GET', KEYS[2]), ARGV[1], ARGV[2]}";
			String[] keys = {"a:b", "a:c"};
			String[] args = {"lettuce script2", "20"};
			args = new String[] {"args11", "args22"};
			String[] params = {keys[0], keys[1], args[0], args[1]};
			
			Object result = client.eval(script, 2, params);
			
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testScript =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testScriptLoad() {
		try {
			String script = "local key = KEYS[1];\n" + 
					"local value = ARGV[1];\n" + 
					"local timeout = ARGV[2];\n" + 
					"redis.call('SETNX', key, value);\n" + 
					"redis.call('EXPIRE', key, timeout);\n" +
					"local result = redis.call('GET', key);\n" + 
					"return result;";
			script = "return {redis.call('GET', KEYS[1]), redis.call('GET', KEYS[2]), ARGV[1], ARGV[2]}";
			String[] keys = {"a:b", "a:c"};
			String[] args = {"lettuce script2", "20"};
			args = new String[] {"args11", "args22"};
			String[] params = {keys[0], keys[1], args[0], args[1]};
			
			// 返回SHA1
			String result = client.scriptLoad(script);
			
			System.out.println(result);
			
			
		} catch (Exception e) {
			log.error("testScript =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testScriptExists() {
		try {
			String sha1 = "8bce5fbb8f4f47fc2b86d20b4909139608e2bcf3";
			Boolean result = client.scriptExists(sha1);
			
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testScript =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testScriptSha1() {
		try {
			String sha1 = "8bce5fbb8f4f47fc2b86d20b4909139608e2bcf3";
			String[] keys = {"a:b", "a:c"};
			String[] args = {"lettuce script2", "20"};
			args = new String[] {"args11", "args22"};
			String[] params = {keys[0], keys[1], args[0], args[1]};
			
			Object result = client.evalsha(sha1, 2, params);
			
			System.out.println(result);
			
			
		} catch (Exception e) {
			log.error("testScript =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testFlush() {
		try {
			// 清空脚本缓存
			client.scriptFlush();
			
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
	//@DisplayName("test")
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 创建一个客户端
	 * @return
	 * @author qianye.zheng
	 */
	private Jedis createClient() {
		/**
		 * socketTimeout 就是readTimeout
		 */
		final Jedis jedis = new Jedis(redisProperties.getHost(), redisProperties.getPort(), 
				redisProperties.getConnectTimeoutMillisec(), redisProperties.getReadTimeoutMillisec());
		jedis.auth(redisProperties.getPassword());
		jedis.select(redisProperties.getDatabase());
		
		return jedis;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
		// 创建客户端
		client = createClient();
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
	}

}
