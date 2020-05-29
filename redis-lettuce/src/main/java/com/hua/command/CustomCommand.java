/**
  * @filename CustomCommand.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.command;

import java.util.List;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @type CustomCommand
 * @description 自定义命令
 * @author qianye.zheng
 */
public interface CustomCommand extends Commands {

	
	/**
	 * 
	 * @description 执行 SET 指令
	 * 问号在参数名前面
	 * @param key
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Command("SET ?0 ?1")
	String set1(final String key, final String value);
	
	/**
	 * 
	 * @description 执行 SET 指令
	 * 通过 @Param 对参数进行命名，在@Command中通过 冒号加参数名来引用
	 * @param key
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Command("SET :key :value")
	String set2(final String key, final String value);
	// @Param注解标注的无效，不标注可以直接使用，原因不明确
	/* 有效的 */
	//@Command("SET :key2 :value2")
	//String set2(final String key2, final String value2);
	/* 无效的 */
	//@Command("SET :key1 :value1")
	//String set2(@Param("key1") final String key, @Param("value1") final String value);
	
	/**
	 * 
	 * @description 执行 SET 指令
	 * 通过 @Param 对参数进行命名，在@Command中通过 冒号加参数名来引用
	 * @param key
	 * @param second 生存时间，秒
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Command("SETEX :key :second :value")
	String setWithExpire(@Param("key") final String key, @Param("seconds") final int second, @Param("value") final String value);
	
	/**
	 * 
	 * @description 执行 SET 指令
	 * 通过 @Param 对参数进行命名，在@Command中通过 冒号加参数名来引用
	 * @param key
	 * @param millisecond 生存时间，毫秒
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Command("PSETEX :key :millisecond :value")
	String setWithExpire2(final String key, final int millisecond, final String value);
	
	/**
	 * 
	 * @description 方法名作为命令
	 * 以redis支持的命名作为方法名，参数列表与redis对该命令的定义保持一致
	 * @param key1
	 * @param key2
	 * @return
	 * @author qianye.zheng
	 */
	@CommandNaming(strategy = CommandNaming.Strategy.METHOD_NAME)
	List<String> mget(final String key1, final String key2);
	
	/**
	 * 
	 * @description 方法名作为命令
	 * 以redis支持的命名作为方法名，参数列表与redis对该命令的定义保持一致
	 * @param keys
	 * @return
	 * @author qianye.zheng
	 */
	@CommandNaming(strategy = CommandNaming.Strategy.METHOD_NAME)
	List<String> mget(final String... keys);
}
