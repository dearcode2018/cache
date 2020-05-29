/**
  * @filename UserTypeConvert.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.convert;

import org.springframework.core.convert.converter.Converter;

import com.hua.entity.UserType;

/**
 * @type UserTypeConvert
 * @description 
 * @author qianye.zheng
 */
public class UserTypeConvert {

	public static final class A implements Converter<UserType, byte[]> {
		
		/**
		 * @description 
		 * @param source
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public byte[] convert(UserType source) {
			return null;
		}
	}
	
	

}
