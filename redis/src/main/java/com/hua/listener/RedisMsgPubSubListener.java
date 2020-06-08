/**
  * @filename RedisMsgPubSubListener.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.listener;

import redis.clients.jedis.Client;
import redis.clients.jedis.JedisPubSub;

/**
 * @type RedisMsgPubSubListener
 * @description Redis消息发布、订阅监听器
 * @author qianye.zheng
 */
public class RedisMsgPubSubListener extends JedisPubSub
{

	/**
	 * @description 
	 * @param channel
	 * @param message
	 * @author qianye.zheng
	 */
	@Override
	public void onMessage(String channel, String message)
	{
		/**
		 * channel 例如 __keyevent@0__:expired
		 */
		System.out.println("RedisMsgPubSubListener.onMessage()");
		 System.out.println("channel:" + channel + " receives message :" + message);  
		 /*
		  * 如果不掉用取消订阅的话，则继续堵塞在此处，等待其他通知
		  */
		 // 取消订阅
		 //this.unsubscribe();
		//super.onMessage(channel, message);
	}

	/**
	 * @description 
	 * @param pattern
	 * @param channel
	 * @param message
	 * @author qianye.zheng
	 */
	@Override
	public void onPMessage(String pattern, String channel, String message)
	{
		super.onPMessage(pattern, channel, message);
	}

	/**
	 * @description 订阅消息
	 * @param channel
	 * @param subscribedChannels
	 * @author qianye.zheng
	 */
	@Override
	public void onSubscribe(String channel, int subscribedChannels)
	{
		System.out.println("RedisMsgPubSubListener.onSubscribe()");
		System.out.println("1.channel:" + channel + " is been subscribed:" + subscribedChannels);  
		//super.onSubscribe(channel, subscribedChannels);
	}

	/**
	 * @description 取消订阅
	 * @param channel
	 * @param subscribedChannels
	 * @author qianye.zheng
	 */
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels)
	{
		System.out.println("RedisMsgPubSubListener.onUnsubscribe()");
		System.out.println("2.channel:" + channel + " is been unsubscribed:" + subscribedChannels);  
		//super.onUnsubscribe(channel, subscribedChannels);
	}

	/**
	 * @description 
	 * @param pattern
	 * @param subscribedChannels
	 * @author qianye.zheng
	 */
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels)
	{
		super.onPUnsubscribe(pattern, subscribedChannels);
	}

	/**
	 * @description 
	 * @param pattern
	 * @param subscribedChannels
	 * @author qianye.zheng
	 */
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels)
	{
		super.onPSubscribe(pattern, subscribedChannels);
	}

	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void unsubscribe()
	{
		super.unsubscribe();
	}

	/**
	 * @description 
	 * @param channels
	 * @author qianye.zheng
	 */
	@Override
	public void unsubscribe(String... channels)
	{
		super.unsubscribe(channels);
	}

	/**
	 * @description 
	 * @param channels
	 * @author qianye.zheng
	 */
	@Override
	public void subscribe(String... channels)
	{
		super.subscribe(channels);
	}

	/**
	 * @description 
	 * @param patterns
	 * @author qianye.zheng
	 */
	@Override
	public void psubscribe(String... patterns)
	{
		super.psubscribe(patterns);
	}

	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void punsubscribe()
	{
		super.punsubscribe();
	}

	/**
	 * @description 
	 * @param patterns
	 * @author qianye.zheng
	 */
	@Override
	public void punsubscribe(String... patterns)
	{
		super.punsubscribe(patterns);
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean isSubscribed()
	{
		return super.isSubscribed();
	}

	/**
	 * @description 
	 * @param client
	 * @param patterns
	 * @author qianye.zheng
	 */
	@Override
	public void proceedWithPatterns(Client client, String... patterns)
	{
		super.proceedWithPatterns(client, patterns);
	}

	/**
	 * @description 
	 * @param client
	 * @param channels
	 * @author qianye.zheng
	 */
	@Override
	public void proceed(Client client, String... channels)
	{
		super.proceed(client, channels);
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int getSubscribedChannels()
	{
		return super.getSubscribedChannels();
	}
	
}
