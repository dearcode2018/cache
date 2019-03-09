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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.mauersu.util.Constant;

/**
 * MonitorConfig
 *
 * @author Jinkai.Ma
 */
@Configuration
@ComponentScan(basePackages = {"com.mauersu.controller", "com.mauersu"})
@Import({WebConfig.class, Security.class})
/* 指定配置文件编码，避免中文乱码 */
@PropertySources({
	@PropertySource(value = "classpath:/application.properties", encoding = Constant.UTF_8), 
	@PropertySource(value = "classpath:/redis.properties", encoding = Constant.UTF_8)
	})
public class MonitorConfig {
}
