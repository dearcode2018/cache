/**
 * MemCachedParam.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean;

import com.hua.constant.Constant;
import com.hua.constant.MemCachedConstant;
import com.hua.util.ArrayUtil;
import com.hua.util.ReadProperties;

/**
 * MemCachedParam
 * 描述: 
 * @author  qye.zheng
 */
public final class MemCachedParam
{
	private static final String splitRegex = ",";
	
	// 注意 * 是正则字符，需要转义才能正常使用
	private static final String splitAsterisk = " \\* ";
	
	// # separator is ,
	/* 服务器列表 */
	private  String[] servers;
	
	// # separator is ,
	/* 服务器权重 */
	private  Integer[] weights;
	
	/* 初始连接数 */
	private  int initConnect;
	
	/* 最小连接数 */
	private  int minConnect;
	
	/* 最大连接数 */
	private  int maxConnect;
	
	/* 最大处理时间 */
	private  long maxIdle;
	
	/* 主线程休眠时间 */
	private long mainThreadSleep;
	
	/* 是否使用nagle算法 */
	private  boolean isNagle;
	
	/*  socket超时 */
	private  int socketTimeOut;
	
	/* socket连接超时 */
	private  int socketConnectTimeOut;
	
	/*是否启用压缩 */
	private  boolean compressEnable;
	
	/* 超过此设置的数据都会被压缩 (单位 : kb) */
	private  long compressThreshold;
	
	/* 读取属性 */
	private ReadProperties props;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public MemCachedParam()
	{
	}

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param props
	 */
	public MemCachedParam(ReadProperties props)
	{
		super();
		this.props = props;
		// 初始化各个属性
		initConfig();
	}

	/**
	 * @return the servers
	 */
	public String[] getServers()
	{
		return servers;
	}

	/**
	 * @param servers the servers to set
	 */
	public void setServers(String[] servers)
	{
		this.servers = servers;
	}

	/**
	 * @return the weights
	 */
	public Integer[] getWeights()
	{
		return weights;
	}

	/**
	 * @param weights the weights to set
	 */
	public void setWeights(Integer[] weights)
	{
		this.weights = weights;
	}

	/**
	 * @return the initConnect
	 */
	public int getInitConnect()
	{
		return initConnect;
	}

	/**
	 * @param initConnect the initConnect to set
	 */
	public void setInitConnect(int initConnect)
	{
		this.initConnect = initConnect;
	}

	/**
	 * @return the minConnect
	 */
	public int getMinConnect()
	{
		return minConnect;
	}

	/**
	 * @param minConnect the minConnect to set
	 */
	public void setMinConnect(int minConnect)
	{
		this.minConnect = minConnect;
	}

	/**
	 * @return the maxConnect
	 */
	public int getMaxConnect()
	{
		return maxConnect;
	}

	/**
	 * @param maxConnect the maxConnect to set
	 */
	public void setMaxConnect(int maxConnect)
	{
		this.maxConnect = maxConnect;
	}

	/**
	 * @return the maxIdle
	 */
	public long getMaxIdle()
	{
		return maxIdle;
	}

	/**
	 * @param maxIdle the maxIdle to set
	 */
	public void setMaxIdle(long maxIdle)
	{
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the minThreadSleep
	 */
	public long getMainThreadSleep()
	{
		return mainThreadSleep;
	}

	/**
	 * @param minThreadSleep the minThreadSleep to set
	 */
	public void setMainThreadSleep(long mainThreadSleep)
	{
		this.mainThreadSleep = mainThreadSleep;
	}

	/**
	 * @return the isNagle
	 */
	public boolean isNagle()
	{
		return isNagle;
	}

	/**
	 * @param isNagle the isNagle to set
	 */
	public void setNagle(boolean isNagle)
	{
		this.isNagle = isNagle;
	}

	/**
	 * @return the socketTimeOut
	 */
	public int getSocketTimeOut()
	{
		return socketTimeOut;
	}

	/**
	 * @param socketTimeOut the socketTimeOut to set
	 */
	public void setSocketTimeOut(int socketTimeOut)
	{
		this.socketTimeOut = socketTimeOut;
	}

	/**
	 * @return the socketConnectTimeOut
	 */
	public int getSocketConnectTimeOut()
	{
		return socketConnectTimeOut;
	}

	/**
	 * @param socketConnectTimeOut the socketConnectTimeOut to set
	 */
	public void setSocketConnectTimeOut(int socketConnectTimeOut)
	{
		this.socketConnectTimeOut = socketConnectTimeOut;
	}

	/**
	 * @return the compressEnable
	 */
	public boolean isCompressEnable()
	{
		return compressEnable;
	}

	/**
	 * @param compressEnable the compressEnable to set
	 */
	public void setCompressEnable(boolean compressEnable)
	{
		this.compressEnable = compressEnable;
	}

	/**
	 * @return the compressThreshold
	 */
	public long getCompressThreshold()
	{
		return compressThreshold;
	}

	/**
	 * @param compressThreshold the compressThreshold to set
	 */
	public void setCompressThreshold(long compressThreshold)
	{
		this.compressThreshold = compressThreshold;
	}

	/**
	 * @return the props
	 */
	public ReadProperties getProps()
	{
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(ReadProperties props)
	{
		this.props = props;
		// 初始化各个属性
		initConfig();
	}
	
	private void initConfig()
	{
		if (null != props)
		{
			servers = props.getProperty(MemCachedConstant.SERVERS).split(splitRegex);
			// String[] --> Integer[]
			String temp;
			String[] strArray = null;
			Integer[] intArray = null;
			strArray = props.getProperty(MemCachedConstant.WEIGHTS).split(splitRegex);
			/*
			 无法实现，抛 java.lang.ArrayStoreException, 需通过数组工具扩展实现
			 */
			// weights = Arrays.copyOf(temp, temp.length, Integer[].class); 
			intArray = ArrayUtil.copyStringToInteger(strArray);
			weights = intArray;
	
			initConnect = Integer.parseInt(props.getProperty(MemCachedConstant.INIT_CONNECT));
			minConnect = Integer.parseInt(props.getProperty(MemCachedConstant.MIN_CONNECT));
			maxConnect =  Integer.parseInt(props.getProperty(MemCachedConstant.MAX_CONNECT));
			
			// 处理 x * y 格式
			temp = props.getProperty(MemCachedConstant.MAX_IDLE);
			if (temp.contains(Constant.ASTERISK))
			{
				// 含有星 (注意 这里是正则表达式)
				strArray = temp.split(splitAsterisk);
				intArray = ArrayUtil.copyStringToInteger(strArray);
				// 调用数组工具，计算出整型数组的乘法结果
				maxIdle = ArrayUtil.multipleResult(intArray);
			} else {
				// 不含有星，直接转
				maxIdle = Long.parseLong(props.getProperty(MemCachedConstant.MAX_IDLE));
			}
			
			// 处理 x * y 格式
			temp = props.getProperty(MemCachedConstant.MAIN_THREAD_SLEEP);
			if (temp.contains(Constant.ASTERISK))
			{
				// 含有星 (注意 这里是正则表达式)
				strArray = temp.split(splitAsterisk);
				intArray = ArrayUtil.copyStringToInteger(strArray);
				// 调用数组工具，计算出整型数组的乘法结果
				mainThreadSleep = ArrayUtil.multipleResult(intArray);
			} else {
				// 不含有星，直接转
				mainThreadSleep = Long.parseLong(props.getProperty(MemCachedConstant.MAIN_THREAD_SLEEP));
			}
			
			isNagle = Boolean.parseBoolean(props.getProperty(MemCachedConstant.IS_NAGLE));
			socketTimeOut = Integer.parseInt(props.getProperty(MemCachedConstant.SOCKET_TIME_OUT));
			socketConnectTimeOut = Integer.parseInt(props.getProperty(MemCachedConstant.SOCKET_CONNECT_TIME_OUT));
			compressEnable = Boolean.parseBoolean(props.getProperty(MemCachedConstant.COMPRESS_ENABLE));
			
			// 处理 x * y 格式
			temp = props.getProperty(MemCachedConstant.COMPRESS_THRESHOLD);
			if (temp.contains(Constant.ASTERISK))
			{
				// 含有星 (注意 这里是正则表达式)
				strArray = temp.split(splitAsterisk);
				intArray = ArrayUtil.copyStringToInteger(strArray);
				// 调用数组工具，计算出整型数组的乘法结果
				compressThreshold = ArrayUtil.multipleResult(intArray);
			} else {
				// 不含有星，直接转
				compressThreshold = Long.parseLong(props.getProperty(MemCachedConstant.COMPRESS_THRESHOLD));
			}
		}
	}
	
}
