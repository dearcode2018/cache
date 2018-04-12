package com.hua.bean;

import java.io.Serializable;
import java.util.Arrays;

public class MemcachedConfig implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// 设置连接池可用的cache服务器列表，server的构成形式是IP:PORT（如：127.0.0.1:11211）
	private String[] servers = new String[] { "localhost:10000" };
	// 设置连接池可用cache服务器的权重，和server数组的位置一一对应
	private Integer[] weights = new Integer[] { 1 };
	// 设置连接失败恢复开关,设置为TRUE，当宕机的服务器启动或中断的网络连接后，这个socket连接还可继续使用，否则将不再使用，默认状态是true，建议保持默认。
	private boolean failback = true;
	// 设置容错开关,设置为TRUE，当当前socket不可用时，程序会自动查找可用连接并返回，否则返回NULL，默认状态是true，建议保持默认。
	private boolean failover = true;
	// 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
	private boolean nagle = false;
	// 设置连接心跳监测开关,设为true则每次通信都要进行连接是否有效的监测，造成通信次数倍增，加大网络负载，因此该参数应该在对HA要求比较高的场合设为TRUE，默认状态是false。
	private boolean aliveCheck = true;
	// 设置开始时每个cache服务器的可用连接数
	private int initConn = 10;
	// 设置每个服务器最少可用连接数
	private int minConn = 3;
	// 设置每个服务器最大可用连接数
	private int maxConn = 1024;
	// 设置可用连接池的最长等待时间
	private int maxIdle = 3000;
	// 设置连接池维护线程的睡眠时间,设置为0，维护线程不启动,维护线程主要通过log输出socket的运行状况，监测连接数目及空闲等待时间等参数以控制连接创建和关闭。
	private int maintSleep = 30;
	// 设置socket的读取等待超时值
	private int socketTO = 3000;

	public String[] getServers()
	{
		return servers;
	}

	public void setServers(String[] servers)
	{
		this.servers = servers;
	}

	public Integer[] getWeights()
	{
		return weights;
	}

	public void setWeights(Integer[] weights)
	{
		this.weights = weights;
	}

	public boolean getFailback()
	{
		return failback;
	}

	public void setFailback(boolean failback)
	{
		this.failback = failback;
	}

	public boolean getFailover()
	{
		return failover;
	}

	public void setFailover(boolean failover)
	{
		this.failover = failover;
	}

	public boolean getNagle()
	{
		return nagle;
	}

	public void setNagle(boolean nagle)
	{
		this.nagle = nagle;
	}

	public boolean getAliveCheck()
	{
		return aliveCheck;
	}

	public void setAliveCheck(boolean aliveCheck)
	{
		this.aliveCheck = aliveCheck;
	}

	public int getInitConn()
	{
		return initConn;
	}

	public void setInitConn(int initConn)
	{
		this.initConn = initConn;
	}

	public int getMinConn()
	{
		return minConn;
	}

	public void setMinConn(int minConn)
	{
		this.minConn = minConn;
	}

	public int getMaxConn()
	{
		return maxConn;
	}

	public void setMaxConn(int maxConn)
	{
		this.maxConn = maxConn;
	}

	public int getMaxIdle()
	{
		return maxIdle;
	}

	public void setMaxIdle(int maxIdel)
	{
		this.maxIdle = maxIdel;
	}

	public int getMaintSleep()
	{
		return maintSleep;
	}

	public void setMaintSleep(int maintSleep)
	{
		this.maintSleep = maintSleep;
	}

	public int getSocketTO()
	{
		return socketTO;
	}

	public void setSocketTO(int socketTO)
	{
		this.socketTO = socketTO;
	}

	@Override
	public String toString()
	{
		return "MemcachedConfig [servers=" + Arrays.toString(servers) + ", weights=" + Arrays.toString(weights) + ", failback=" + failback + ", failover=" + failover + ", nagle=" + nagle
				+ ", aliveCheck=" + aliveCheck + ", initConn=" + initConn + ", minConn=" + minConn + ", maxConn=" + maxConn + ", maxIdle=" + maxIdle + ", maintSleep=" + maintSleep + ", socketTO="
				+ socketTO + "]";
	}
}
