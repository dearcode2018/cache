/**
  * @filename UserTypeReadConvert.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.hua.entity.User;

/**
 * @type UserTypeReadConvert
 * @description 用户类型转换器
 * @author qianye.zheng
 */
@ReadingConverter
public class UserTypeReadConvert implements Converter<byte[], User.Address> {

	// TODO 这2个转换器不起作用，原因未名
	
	/**
	 * @description 
	 * @param source
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public User.Address convert(byte[] source) {
		return null;
	}

}
