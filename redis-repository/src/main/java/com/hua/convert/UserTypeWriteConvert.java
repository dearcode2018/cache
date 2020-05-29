/**
  * @filename UserTypeWriteConvert.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.hua.entity.User;

/**
 * @type UserTypeWriteConvert
 * @description 用户类型转换器
 * @author qianye.zheng
 */
@WritingConverter
public class UserTypeWriteConvert implements Converter<User.Address, byte[]> {

	// TODO 这2个转换器不起作用，原因未名
	/**
	 * @description 
	 * @param source
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public byte[] convert(User.Address source) {
		
		return null;
	}
	

}
