/**
 * 描述: 
 * RepositoryTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.repo;

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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
import com.hua.entity.User;
import com.hua.entity.UserType;
import com.hua.repository.UserRepository;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * RepositoryTest
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
public final class RepositoryTest extends BaseTest {

	
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
	
	@Resource
	private UserRepository userRepository;
	
	/**
	 * 对象解析为Hash Maping
	 * 用redis的hash结构来存储，
	 * id组成key，field-value 键值对
	 * 
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
	public void testRepository() {
		try {
			User entity = new User();
			entity.setId("1234");
			entity.setUsername("zhangsan");
			entity.setNickname("张三");
			entity.setValid(true);
			entity.setLastLoginTime(DateTimeUtil.localDateTime());
			
			User ret = userRepository.save(entity);
			System.out.println(JacksonUtil.writeAsString(ret));
			
		} catch (Exception e) {
			log.error("testRepository =====> ", e);
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
	public void testSave() {
		try {
			User entity = new User();
			entity.setId("1234");
			entity.setUsername("zhangsan");
			entity.setNickname("张三");
			entity.setValid(true);
			entity.setType(UserType.NORMAL);
			entity.setLastLoginTime(DateTimeUtil.localDateTime());
			User.Address address = new User.Address();
			address.setProvince("广东省");
			address.setCity("广州市");
			address.setDetail("新港东路10号");
			entity.setAddress(address);
			entity.setRoles(Arrays.asList(1, 2, 4, 5, 0, 3));
			Map<Integer, String> roleResource = new HashMap<>();
			roleResource.put(10, "菜单1");
			roleResource.put(12, "菜单3");
			entity.setRoleResources(roleResource);
			// 返回当前传入的参数，暂时无意义
			User ret = userRepository.save(entity);
			System.out.println(ret.getId());
			
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
	public void testSaveWithoutId() {
		try {
			/**
			 * 没有设置id，redis会自动生成id，然后设置到实体中
			 * uuid
			 */
			User entity = new User();
			entity.setUsername("zhangsan1");
			entity.setNickname("张三1");
			entity.setValid(true);
			//entity.setLastLoginTime(DateTimeUtil.getTimestamp());
			// 返回当前传入的参数，获取返回的id
			User ret = userRepository.save(entity);
			System.out.println(JacksonUtil.writeAsString(ret));
			
		} catch (Exception e) {
			log.error("testSaveWithoutId =====> ", e);
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
	public void testDelete() {
		try {
			/*
			 * 删除之前，会先根据id去查询
			 * 因此，如果查询失败(反序列化等)，则删除也失败
			 */
			userRepository.deleteById("1234");
			
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
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
	public void testDelete2() {
		try {
			User entity = new User();
			entity.setId("1234");
			entity.setUsername("zhangsan");
			entity.setNickname("张三4");
			entity.setValid(true);
			//entity.setLastLoginTime(DateTimeUtil.getTimestamp());
			
			// 根据对象去删除，本质也是根据id去删除
			userRepository.delete(entity);
			
		} catch (Exception e) {
			log.error("testDelete2 =====> ", e);
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
	public void testUpdate() {
		try {
			User entity = new User();
			entity.setId("1234");
			entity.setUsername("zhangsan");
			entity.setNickname("张三2");
			entity.setValid(true);
			//entity.setLastLoginTime(DateTimeUtil.getTimestamp());
			
			// 更新，返回当前传入的参数，暂时无意义
			User ret = userRepository.save(entity);
			System.out.println(JacksonUtil.writeAsString(ret));
			
		} catch (Exception e) {
			log.error("testUpdate =====> ", e);
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
	public void testGet() {
		try {
			userRepository.findById("1234").ifPresent(System.out :: println);
			
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
	//@DisplayName("test")
	@Test
	public void testExists() {
		try {
			
			String id = "1234";
			assertTrue(userRepository.existsById(id));
		} catch (Exception e) {
			log.error("testExists =====> ", e);
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
