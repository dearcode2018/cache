/**
 * 描述: 
 * ServerTest.java
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

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.DebugParams;
import redis.clients.jedis.JedisMonitor;

import com.hua.client.CacheMonitor;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ServerTest
 */
public final class ServerTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBGRewriteAOF() {
		try {
		
			
		} catch (Exception e) {
			log.error("testBGRewriteAOF =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBGSave() {
		try {
			/*
			 * 在后台异步保存当前数据库的数据到磁盘
			 */
			String resultCode = jedis.bgsave();
			log.info("testBGSave =====> resultCode = " + resultCode);
			
		} catch (Exception e) {
			log.error("testBGSave =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testClient() {
		try {
		
			
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
	@Test
	public void testClientGetName() {
		try {
			/*
			 * 设置连接名称: clientSetName
			 */
			String name = "haha";
			String result = jedis.clientSetname(name);
			log.info("testClientGetName =====> result = " + result);
			/*
			 * 获取连接名称
			 * 设置连接名称: clientSetName
			 */
			name = jedis.clientGetname();
			log.info("testClientGetName =====> name = " + name);
		} catch (Exception e) {
			log.error("testClientGetName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testClientKill() {
		try {
			/*
			 * 关闭指定IP:port的客户端
			 * ip:port 应该和 CLIENT LIST 命令输出的其中一行匹配。
			 */
			String client = "";
			//String client = "127.0.0.1:6379";
			String result = jedis.clientKill(client);
			log.info("testClientKill =====> result = " + result);
		} catch (Exception e) {
			log.error("testClientKill =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testClientList() {
		try {
			/*
			 *  id=81 addr=127.0.0.1:65249 fd=6 name= age=0 idle=0 
			 *  flags=N db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=32768 
			 *  obl=0 oll=0 omem=0 events=r cmd=client
			 */
			String clientList = jedis.clientList();
			System.out.println(clientList);
		} catch (Exception e) {
			log.error("testClientList =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testClientSetName() {
		try {
			/*
			 * 设置连接名称: clientSetName
			 */
			String name = "haha";
			String result = jedis.clientSetname(name);
			log.info("testClientSetName =====> result = " + result);
			/*
			 * 获取连接名称
			 * 设置连接名称: clientSetName
			 */
			name = jedis.clientGetname();
			log.info("testClientSetName =====> name = " + name);
		} catch (Exception e) {
			log.error("testClientSetName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConfig() {
		try {
		
			
		} catch (Exception e) {
			log.error("testConfig =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConfigGet() {
		try {
			/*
			 * config get 获取运行中redis服务器的配置参数
			 * 键值对:参数和配置值
			 */
			String pattern = "s*";
			pattern  = "require*";
			List<String> values = jedis.configGet(pattern);
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testConfigGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConfigResetStat() {
		try {
			/*
			 * 重置 INFO 命令中的某些统计数据，包括：
			 * Keyspace hits (键空间命中次数)
			 * Keyspace misses (键空间不命中次数)
			 * Number of commands processed (执行命令的次数)
			 * Number of connections received (连接服务器的次数)
			 * Number of expired keys (过期key的数量)
			 * Number of rejected connections (被拒绝的连接数量)
			 * Latest fork(2) time(最后执行 fork(2) 的时间)
			 * The aof_delayed_fsync counter(aof_delayed_fsync 计数器的值)
			 */
			
		} catch (Exception e) {
			log.error("testConfigResetStat =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConfigRewrite() {
		try {
			/*
			 * 将当前redis服务器的配置记录到redis.conf中
			 * config set 可以修改服务器的配置
			 */
			
			
		} catch (Exception e) {
			log.error("testConfigRewrite =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConfigSet() {
		try {
			/*
			 * 
			 */
			String parameter = "requirepass";
			String value = "redis";
			value = "";
			String result = jedis.configSet(parameter, value);
			log.info("testConfigSet =====> result = " + result);
		} catch (Exception e) {
			log.error("testConfigSet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDBSize() {
		try {
			/*
			 * 获取当前数据库key的数量
			 */
			Long size = jedis.dbSize();
			log.info("testDBSize =====> size = " + size);
		} catch (Exception e) {
			log.error("testDBSize =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDebugObject() {
		try {
			/*
			 * 调试命令，获取一个调试信息
			 */
			String key = "COM:USERS";
			DebugParams params = DebugParams.OBJECT(key);
			String result = jedis.debug(params);
			log.info("testDebugObject =====> result = " + result);
		} catch (Exception e) {
			log.error("testDebugObject =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDebugSegFault() {
		try {
			/*
			 * 调试命令，执行一个不合法的内存访问从而让
			 * redis奔溃，仅在开发时用于debug模拟.
			 */
			String key = "COM:USERS";
			DebugParams params = DebugParams.SEGFAULT();
			String result = jedis.debug(params);
			log.info("testDebugSegFault =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testDebugSegFault =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFlushAll() {
		try {
			/*
			 * 删除redis服务器所有数据库的key
			 */
			String result = jedis.flushAll();
			log.info("testFlushAll =====> result = " + result);
		} catch (Exception e) {
			log.error("testFlushAll =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFlushDB() {
		try {
			/*
			 * 删除redis服务器当前数据库的key
			 */
			/*
			 * 切换指定的数据库，数据库索引index
			 * 用数字指定，从0开始，默认使用0
			 */
			int index = 2;
			String resultCode =jedis.select(index);
			String result = jedis.flushDB();
			log.info("testFlushDB =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testFlushDB =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInfo() {
		try {
			/*
			# Server
			redis_version:2.8.19
			redis_git_sha1:00000000
			redis_git_dirty:0
			redis_build_id:9968db13395be4aa
			redis_mode:standalone
			os:Windows  
			arch_bits:64
			multiplexing_api:winsock_IOCP
			gcc_version:0.0.0
			process_id:17428
			run_id:ebb9a922b501f9044c18fa4076d7dd8c8729db71
			tcp_port:6379
			uptime_in_seconds:271
			uptime_in_days:0
			hz:10
			lru_clock:8973456
			config_file:D:\software\redis-2.8\redis.windows.conf
			
			# Clients
			connected_clients:1
			client_longest_output_list:0
			client_biggest_input_buf:0
			blocked_clients:0
			
			# Memory
			used_memory:4360928
			used_memory_human:4.16M
			used_memory_rss:4309584
			used_memory_peak:4402288
			used_memory_peak_human:4.20M
			used_memory_lua:35840
			mem_fragmentation_ratio:0.99
			mem_allocator:dlmalloc-2.8
			
			# Persistence
			loading:0
			rdb_changes_since_last_save:34
			rdb_bgsave_in_progress:0
			rdb_last_save_time:1451813985
			rdb_last_bgsave_status:ok
			rdb_last_bgsave_time_sec:-1
			rdb_current_bgsave_time_sec:-1
			aof_enabled:0
			aof_rewrite_in_progress:0
			aof_rewrite_scheduled:0
			aof_last_rewrite_time_sec:-1
			aof_current_rewrite_time_sec:-1
			aof_last_bgrewrite_status:ok
			aof_last_write_status:ok
			
			# Stats
			total_connections_received:9
			total_commands_processed:29
			instantaneous_ops_per_sec:0
			total_net_input_bytes:2290
			total_net_output_bytes:0
			instantaneous_input_kbps:0.00
			instantaneous_output_kbps:0.00
			rejected_connections:0
			sync_full:0
			sync_partial_ok:0
			sync_partial_err:0
			expired_keys:0
			evicted_keys:0
			keyspace_hits:0
			keyspace_misses:0
			pubsub_channels:0
			pubsub_patterns:0
			latest_fork_usec:0
			
			# Replication
			role:master
			connected_slaves:0
			master_repl_offset:0
			repl_backlog_active:0
			repl_backlog_size:1048576
			repl_backlog_first_byte_offset:0
			repl_backlog_histlen:0
			
			# CPU
			used_cpu_sys:0.03
			used_cpu_user:0.05
			used_cpu_sys_children:0.00
			used_cpu_user_children:0.00
			
			# Keyspace
			 */
			String info = jedis.info();
			System.out.println(info);
		} catch (Exception e) {
			log.error("testInfo =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInfo2() {
		try {
			/*
			# Server
			# Clients
			# Memory
			# Persistence
			# Stats
			# Replication
			# CPU
			# Keyspace
			 */
			String section = "Memory";
			section = "Clients";
			String info = jedis.info(section);
			System.out.println(info);
		} catch (Exception e) {
			log.error("testInfo2 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLastSave() {
		try {
			/*
			 * 获取最近一次redis成功将数据保存到磁盘
			 * 的时间，以unix时间戳格式展示 (秒)
			 */
			long time = jedis.lastsave();
			// 1451814844.072332 [0 127.0.0.1:51635] "命令"
			log.info("testLastSave =====> time = " + time);
			
		} catch (Exception e) {
			log.error("testLastSave =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMonitor() {
		try {
			/*
			 * 实时打印出redis服务器接收到的命令，调试用
			 */
			JedisMonitor jedisMonitor = new CacheMonitor();
			jedis.monitor(jedisMonitor);
			
		} catch (Exception e) {
			log.error("testMonitor =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPSync() {
		try {
		
			
		} catch (Exception e) {
			log.error("testPSync =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSave() {
		try {
			/*
			 * 执行一个同步保存操作，将当前redis实例
			 * 所有数据快照以DB文件形式保存到硬盘
			 */
			String result = jedis.save();
			log.info("testSave =====> result = " + result);
		} catch (Exception e) {
			log.error("testSave =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testShutdown() {
		try {
			/*
			 * 停止redis服务器
			 */
			String result = jedis.shutdown();
			log.info("testShutdown =====> result = " + result);
		} catch (Exception e) {
			log.error("testShutdown =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSlaveOf() {
		try {
			/*
			 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)。
			 */
			String host = "127.0.0.1";
			int port = 6379;
			String result =jedis.slaveof(host, port);
			log.info("testSlaveOf =====> result = " + result);
		} catch (Exception e) {
			log.error("testSlaveOf =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSlowLog() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSlowLog =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSync() {
		try {
		
			
		} catch (Exception e) {
			log.error("testSync =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTime() {
		try {
			/*
			 * 返回当前服务器的时间
			 */
			List<String> values = jedis.time();
			for (String value : values)
			{
				System.out.print(value + ", ");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testTime =====> ", e);
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
