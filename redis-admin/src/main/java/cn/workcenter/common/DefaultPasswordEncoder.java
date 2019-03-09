/**
  * @filename DefaultPasswordEncoder.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package cn.workcenter.common;

import org.springframework.security.crypto.password.PasswordEncoder;
	
/**
 * @type DefaultPasswordEncoder
 * @description 
 * @author qianye.zheng
 */
public class DefaultPasswordEncoder implements PasswordEncoder
{

	/**
	 * @description 
	 * @param rawPassword
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String encode(CharSequence rawPassword)
	{
		return rawPassword.toString();
	}

	/**
	 * @description 
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		return encodedPassword.equals(rawPassword.toString());
	}

}
