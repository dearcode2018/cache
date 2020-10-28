/**
  * @filename OneAnnotation.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, TYPE_PARAMETER })
/**
 * @type OneAnnotation
 * @description 
 * @author qianye.zheng
 */
public @interface OneAnnotation {

	//public static final int a = 1;
	
	enum EN {
	}
	
	// 默认是static类型
	class A {
		
	}
	
}
