/**
 * Copyright 2006-2015 handu.com
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mauersu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cn.workcenter.common.DefaultPasswordEncoder;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

   //@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(env.getProperty("manager.username", "root"))
                .password(env.getProperty("manager.password", "password"))
                .roles("MANAGER");
        //这样，页面提交时候，密码以明文的方式进行匹配。
        auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder()).withUser("cxh").password("cxh").roles("ADMIN");			
    }
   
   
   /**
    * 
    * @description 
    * @param http
    * @throws Exception
    * @author qianye.zheng
    */
   @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//关闭默认的csrf认证
/*        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();*/
	   
	   /*
	    * 自定义登录页面
	    * 使用了自定义登录页就无法再用Spring自带的登录逻辑了
	    * 需要自己写一套
	    */
/*	   http.formLogin().loginPage("/dist/login.html")
	   .and().authorizeRequests().antMatchers("/css/**", "/dist/login.html", "/login/**").permitAll().anyRequest()
	   .authenticated().and().logout().permitAll();*/
	   
    	http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
    	.and().logout().permitAll().and().formLogin();
        //关闭默认的csrf认证
        http.csrf().disable();
    }
    

	/**
		 * @description 
		 * @param auth
		 * @throws Exception
		 * @author qianye.zheng
		 */
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
/*	        auth.inMemoryAuthentication()
            .withUser(env.getProperty("manager.username", "root"))
            .password(env.getProperty("manager.password", "password"))
            .roles("MANAGER");*/
	        //这样，页面提交时候，密码以明文的方式进行匹配。
	        auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder()).withUser(env.getProperty("manager.username", "admin"))
	        	.password(env.getProperty("manager.password", "admin")).roles("ADMIN");			
			
			
			 //可以设置内存指定的登录的账号密码,指定角色
	        //不加.passwordEncoder(new DefaultPasswordEncoder())
	        //就不是以明文的方式进行匹配，会报错
	        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	        //.passwordEncoder(new MyPasswordEncoder())。
	        //这样，页面提交时候，密码以明文的方式进行匹配。
	       // auth.inMemoryAuthentication().passwordEncoder(new DefaultPasswordEncoder()).withUser("cxh").password("cxh").roles("ADMIN");			
		}
    
}