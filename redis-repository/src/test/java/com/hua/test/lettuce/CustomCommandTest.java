/**
 * 描述: 
 * CustomCommandTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.lettuce;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.hua.command.CustomCommand;
import com.hua.test.BaseTest;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.dynamic.RedisCommandFactory;
import reactor.core.publisher.Mono;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CustomCommandTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class CustomCommandTest extends BaseTest {

	
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
	 * lettuce要点
	 * 1) RedisURI
	 * 2) RedisClient/RedisClusterClient
	 * 3) StatefulConnection/StatefulRedisConnection/StatefulRedisPubSubConnection
	 * 连接类型: 单点、哨兵、集群、发布/订阅
	 * 4) RedisCommands/RedisAsyncCommands/RedisReactiveCommands
	 * 命令类型: 同步、异步、反应式(reative)
	 * 
	 * 
	 */
	
	private RedisClient client;
	
	private StatefulRedisConnection<String, String> connection;
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testCustomCommand() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建命令工厂
			RedisCommandFactory factory = new RedisCommandFactory(connection);
			// 创建命令
			CustomCommand command = factory.getCommands(CustomCommand.class);
			String key = "a:custom";
			// 执行具体的命令
			command.set1(key, "custom command1");
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testCustomCommand =====> ", e);
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
	public void testCustomCommand2() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建命令工厂
			RedisCommandFactory factory = new RedisCommandFactory(connection);
			// 创建命令
			CustomCommand command = factory.getCommands(CustomCommand.class);
			String key = "a:custom2";
			// 执行具体的命令
			command.set2(key, "custom command2");
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testCustomCommand2 =====> ", e);
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
	public void testSetWithExpire() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建命令工厂
			RedisCommandFactory factory = new RedisCommandFactory(connection);
			// 创建命令
			CustomCommand command = factory.getCommands(CustomCommand.class);
			String key = "a:custom2";
			// 执行具体的命令
			command.setWithExpire(key, 20, "custom command setWithExpire");
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testSetWithExpire =====> ", e);
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
	public void testSetWithExpire2() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建命令工厂
			RedisCommandFactory factory = new RedisCommandFactory(connection);
			// 创建命令
			CustomCommand command = factory.getCommands(CustomCommand.class);
			String key = "a:custom2";
			// 执行具体的命令
			command.setWithExpire2(key, 10 * 1000, "custom command setWithExpire");
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testSetWithExpire =====> ", e);
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
	public void testCommandNaming() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建命令工厂
			RedisCommandFactory factory = new RedisCommandFactory(connection);
			// 创建命令
			CustomCommand command = factory.getCommands(CustomCommand.class);
			String key = "a:custom";
			String key2 = "a:custom2";
			List<String> results = command.mget(key, key2);
			results.forEach(System.out :: println);
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testCommandNaming =====> ", e);
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
	public void testScript() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建同步命令
			RedisCommands<String, String> commands = connection.sync();
			String result = null;
			String script = "local key = KEYS[1];\n" + 
					"local value = ARGV[1];\n" + 
					"local timeout = ARGV[2];\n" + 
					"redis.call('SETNX', key, value);\n" + 
					"redis.call('EXPIRE', key, timeout);\n" +
					"local result = redis.call('GET', key);\n" + 
					"return result;";
			String[] keys = {"a:b"};
			String[] args = {"lettuce script2", "20"};
			args = new String[] {"lettuce script2"};
			result = commands.eval(script, ScriptOutputType.VALUE, keys, args);
			System.out.println(result);

			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
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
	public void testScript2() {
		try {
			RedisURI redisUri = RedisURI.create(redisProperties.getUri());
			redisUri.setTimeout(redisProperties.getReadTimeout());
			// 客户端
			RedisClient client = RedisClient.create(redisUri);
			// 创建线程安全连接
			StatefulRedisConnection<String, String> connection = client.connect();
			// 创建同步命令
			RedisCommands<String, String> commands = connection.sync();
			String result = null;
			String script = "local key = KEYS[1];" + 
					"local value = ARGV[1];" + 
					"local timeout = ARGV[2];" + 
					"redis.call('SETNX', key, value);" + 
					"redis.call('EXPIRE', key, timeout);" +
					"local result = redis.call('GET', key);" + 
					"return result;";
			
			// 存在才设置
			script = "local key = KEYS[1];" + 
					"local value = ARGV[1];" + 
					"local timeout = ARGV[2]" +
					"local result = redis.call('SETEX', key, timeout, value);" +
					"return result;";
			
			String[] keys = {"a:b"};
			String[] args = {"lettuce script2", "20"};
			//result = commands.eval(script, ScriptOutputType.VALUE, keys, args);
			Boolean result2 = commands.eval(script, ScriptOutputType.BOOLEAN, keys, args);
			System.out.println(result2);
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testScript2 =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 异步命令
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testAsync() {
		try {
			// 创建同步命令
			RedisAsyncCommands<String, String> commands = connection.async();
			String key = "a:b";
			RedisFuture<String> result = null;
			SetArgs args = SetArgs.Builder.nx().ex(30);
			result = commands.set(key, "new lettuce", args);
			
			while (true) { // 循环确认
				System.out.println("确认中...");
				assertTrue("OK".equalsIgnoreCase(result.get()));
				if (result.isDone()) { // 完成则结束询问
					break;
				}
			}
			
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testAsync =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: reactive 反应式
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testReactive() {
		try {
			/**
			 * 反应式
			 * 1) 返回结果只有0或1个元素，则返回值类型为Mono
			 * 2) 返回结果为多个，则返回值类型为Flux
			 */
			// 创建同步命令
			RedisReactiveCommands<String, String> commands = connection.reactive();
			String key = "a:b";
			Mono<String> result = null;
			SetArgs args = SetArgs.Builder.nx().ex(30);
			result = commands.set(key, "new lettuce", args);
			
			/*
			 * 订阅结果
			 * 结果只能订阅一次，多次订阅会提示 连接已经关闭 Connection is already closed
			 */
			//result.subscribe(x -> assertTrue("OK".equalsIgnoreCase(x)));
			result.subscribe(System.out :: println);
			// 避免连接过早关闭
			TimeUnit.SECONDS.sleep(3);
			// 关闭连接
			connection.close();
			// 关闭客户端
			client.shutdown();
			
		} catch (Exception e) {
			log.error("testReactive =====> ", e);
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
		RedisURI redisUri = RedisURI.create(redisProperties.getUri());
		redisUri.setTimeout(redisProperties.getReadTimeout());
		// 客户端
		client = RedisClient.create(redisUri);
		// 创建线程安全连接
		connection = client.connect();
		// 非单点模式下，可设置读取偏好
		// StatefulRedisMasterSlaveConnection.setReadFrom(ReadFrom)
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
		// 关闭连接
		connection.close();
		// 关闭客户端
		client.shutdown();
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