/**
 * 描述: 
 * EhcacheTest.java
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

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.cache.CustomExpiry;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * EhcacheTest
 */
public final class EhcacheTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCacheManager() {
		try {
			/**
			 * 
			 */
			CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured", 
					CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))).build();
			
			cacheManager.init();
			
			   Cache<Long, String> preConfigured =
				        cacheManager.getCache("preConfigured", Long.class, String.class); 
			
		    Cache<String, String> myCache = cacheManager.createCache("myCache", 
		            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)).build());
		    
/*		    for (int i = 0; i < 20; i++)
		    {
		        //写
		        myCache.put(i + "", "@"+i);
		    }
		    
		    for (int i = 0; i < 20; i++)
		    {
		        //读
		        String value = myCache.get(i + "");
		        System.out.println("get at "+i+":"+value);
		    }
		    */
	        //写
	        //myCache.put("key", "haha");
	        String value = myCache.get("key");
	        System.out.println("value : " + value);
		    
		    //cacheManager.removeCache("preConfigured"); 
		   // cacheManager.close(); 
		} catch (Exception e) {
			log.error("testCacheManager =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCacheManager2() {
		try {
			 PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
					 // .with(CacheManagerBuilder.persistence("D:" + File.separator + "myData")) 
					 // C:\Users\admin\AppData\Local\Temp
					 .with(CacheManagerBuilder.persistence(System.getProperty("java.io.tmpdir") + "ehcache"))
							 .withCache("threeTieredCache",
				            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
				                ResourcePoolsBuilder.newResourcePoolsBuilder()
				                    .heap(10, EntryUnit.ENTRIES)  //堆
				                    .offheap(10, MemoryUnit.MB)    //堆外
				                    .disk(20, MemoryUnit.GB)     //磁盘
				                ).withExpiry(new CustomExpiry())
					// 是否需要初始化
					).build(true);
			 // 
			Cache<String, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", String.class, String.class);
			// 写
		for (int i = 0; i <= 2000; i++)
			{
				threeTieredCache.put(i + "", "$" + i);
			}

		//Thread.sleep(5 * 1000);
		
			// 读
			for (int i = 0; i <= 2000; i++)
			{
				String value = threeTieredCache.get(i + "");
				System.out.println("get at " + i + ":" + value);
			}
			// 关闭缓存，删除缓存文件, 不删除会在磁盘留下数据文件
			//persistentCacheManager.close();
			
		} catch (Exception e) {
			log.error("testCacheManager2 =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetCache() {
		try {
			 // 构建一个缓存管理器
	        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
	        // cacheManager.init();
	        // cacheConfiguration -100个换成最大，缓存过期时间4秒
	        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder
	                .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(100))
	                .withExpiry(new CustomExpiry()).build();
	        // 根据配置创建一个缓存容器
	        Cache<String, String> myCache = cacheManager.createCache("myCache", cacheConfiguration);
	        // 设置一个值
	        myCache.put("testKey", "testValue");
	        // 循环直到数据过期 否包含该key
	        while (myCache.containsKey("testKey")) {
	            try {
	                System.out.println("值:" + myCache.get("testKey"));
	                Thread.sleep(2000);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("缓存已过期");
	        cacheManager.close();
			
		} catch (Exception e) {
			log.error("testGetCache =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetProperties() {
		try {
			System.out.println(System.getProperty("java.io.tmpdir"));
		} catch (Exception e) {
			log.error("testGetProperties =====> ", e);
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
