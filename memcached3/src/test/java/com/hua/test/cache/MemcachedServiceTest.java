/**
 * 描述: 
 * MemcachedServiceTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.cache;

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

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.entity.Person;
import com.hua.service.MemcachedService;
import com.hua.service.impl.MemcachedServiceImpl;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * MemcachedServiceTest
 */
public final class MemcachedServiceTest extends BaseTest {

	// 单例化
	private static MemcachedService memcachedService = MemcachedServiceImpl.getInstance();
	
	private String key = "TestKey";
	
	private Object value;
	
	private Date expiryDt;
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMemcachedService() {
		try {
			Person p = new Person();
			p.setName("杨烁");
			p.setNation("汉");
			p.setBirthday(DateTimeUtil.getDate());
			p.setAddress("湖北省襄阳市汉阳区东山路152号");
			p.setGender(Gender.MALE);
			p.setPhotoUrl("http://www.come.here/m/tp.gif");
			System.out.println(memcachedService);
			value = p;
			memcachedService.set(key, value);
			
		} catch (Exception e) {
			log.error("testMemcachedService =====> ", e);
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
			Person p = new Person();
			p.setName("杨烁");
			p.setNation("汉");
			p.setBirthday(DateTimeUtil.getDate());
			p.setAddress("湖北省襄阳市汉阳区东山路152号");
			p.setGender(Gender.MALE);
			p.setPhotoUrl("http://www.come.here/m/tp.gif");
			value = p;
			// 失效日期
			expiryDt = DateTimeUtil.parseDateTime("2015-02-09 15:33:00");
			// 不存在则add，存在则replace. (成功则返回true，失败则返回false)
			//log.info("testSet =====> " + memcachedService.set(key, value));
			log.info("testSet =====> " + memcachedService.set(key, value, expiryDt));
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
	public void testAdd() {
		try {
			Person p = new Person();
			p.setName("杨烁");
			p.setNation("汉");
			p.setBirthday(DateTimeUtil.getDate());
			p.setAddress("湖北省襄阳市汉阳区东山路152号");
			p.setGender(Gender.MALE);
			p.setPhotoUrl("http://www.come.here/m/tp.gif");
			value = p;
			// 存在则返回 false 不添加，不存在则添加(成功则返回true，失败则返回false)
			log.info("testAdd =====> " + memcachedService.add(key, value));
			
		} catch (Exception e) {
			log.error("testAdd =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReplace() {
		try {
			Person p = new Person();
			p.setName("杨烁");
			p.setNation("汉");
			p.setBirthday(DateTimeUtil.getDate());
			p.setAddress("湖北省襄阳市汉阳区东山路152号");
			p.setGender(Gender.MALE);
			p.setPhotoUrl("http://www.come.here/m/tp.gif");
			value = p;
			// 不存在则返回false，否则执行更新替换(成功则返回true，失败则返回false)
			log.info("testReplace =====> " + memcachedService.replace(key, value));
			
		} catch (Exception e) {
			log.error("testReplace =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			// 不存在则返回false, 存在则删除(成功则返回true，失败则返回false)
			log.info("testDelete =====> " + memcachedService.delete(key));
			// log.info("testDelete =====> " + memcachedService.delete(key + "dd"));
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
	@Test
	public void testGet() {
		try {
			// 缓存不存在则返回 null
			value = memcachedService.get(key);
			Person p = (Person) value;
			
			log.info("testGet =====> value = " + p.getName());
			log.info("testGet =====> " + p.getBirthday());
			
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
