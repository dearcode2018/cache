/**
  * @filename LoginController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.mauersu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @type LoginController
 * @description 
 * @author qianye.zheng
 */
//@Controller
//@RequestMapping("/login")
public class LoginController
{	
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	//@PostMapping("/pwd")
	public Object loginPwd(HttpServletRequest request, HttpServletResponse response) 
	{
		
		//System.out.println("LoginController.loginPwd()1");
		
		return "redirect:/index";
	}
	
}
