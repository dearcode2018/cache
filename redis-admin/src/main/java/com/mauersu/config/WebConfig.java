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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Jinkai.Ma
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	
	/**
	 * 
	 * @description 
	 * @param registry
	 * @author qianye.zheng
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	/*
    	 * 静态资源访问
    	 */
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("css/");
        registry.addResourceHandler("/img/**").addResourceLocations("img/");
        registry.addResourceHandler("/js/**").addResourceLocations("js/");
        registry.addResourceHandler("/dist/**").addResourceLocations("dist/");
        registry.addResourceHandler("/bower_components/**").addResourceLocations("bower_components/");
        registry.addResourceHandler("/sb-admin/**").addResourceLocations("sb-admin/");
    }

    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
    	
    	InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setOrder(1);
        internalResourceViewResolver.setContentType("text/html; charset=utf-8");
        // 控制层 返回 相对路径时，拼接上前缀值 ( /view/ )
        internalResourceViewResolver.setPrefix("/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        
        return internalResourceViewResolver;
    }
    
}
