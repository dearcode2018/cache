/**
 * 描述: 
 * PipelineTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.pipeline;

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
import redis.clients.jedis.Pipeline;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PipelineTest
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
public final class PipelineTest extends BaseTest {

	
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
	 * pipeline: jedis底层采用BIO(阻塞IO)通讯，客户端缓存所有要发送的命令，最后触发同步发送一个命令列表包，
	 * 再接收和解析一个响应列表包.
	 *
	 * 
	 * 
	 * 
	 */
	
	private Jedis client;
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testPipeline() {
		try {
			//String response = client.info();
			//System.out.println(response);
			String key = null;
			key = "a:b";
			Pipeline pipeline = client.pipelined();
			pipeline.set(key, "pipeline");
			pipeline.expire(key, 10);

			
			/*
			 * 同步执行，并且返回所有结果
			 */
			List<Object> result = pipeline.syncAndReturnAll();
			result.forEach(System.out :: println);
			
			/*
			 * 断开连接，断开和服务端的连接
			 */
			client.disconnect();
		} catch (Exception e) {
			log.error("testPipeline =====> ", e);
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
	public void testClient() {
		try {
			//String response = client.info();
			//System.out.println(response);
			
			String response = client.ping();
			System.out.println(response);
			
			/*
			 * 断开连接，断开和服务端的连接
			 */
			client.disconnect();
		} catch (Exception e) {
			log.error("testClient =====> ", e);
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
	public void testCreateClient() {
		try {
			/**
			 * socketTimeout 就是readTimeout
			 */
			/*
			 * 标准格式:  (由于redis暂时没有user的概念，因此保持为空)
			 * redis://user:password@ip:6379/database
			 * 
			 * 使用格式:
			 * redis://:password@ip:port/database
			 */
			//String val = "redis://:" + redisProperties.getPassword() + "@" + redisProperties.getHost() + ":" + redisProperties.getPort();
			//URI uri = new URI(val);
			System.out.println(redisProperties.getUri());
			final Jedis jedis = new Jedis(redisProperties.getUri(), redisProperties.getConnectTimeoutMillisec(), redisProperties.getReadTimeoutMillisec());
			//final Jedis jedis = new Jedis(redisProperties.getUri());
			//jedis.auth(redisProperties.getPassword());
			//jedis.select(redisProperties.getDatabase());
			
			String response = jedis.ping();
			System.out.println(response);
			
			jedis.set("a:b", "value11");
	
			/*
			 * 断开连接，断开和服务端的连接
			 */
			jedis.disconnect();
			jedis.close();
		} catch (Exception e) {
			log.error("testCreateClient =====> ", e);
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
		// 关闭连接，客户端失效
		client.disconnect();
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
