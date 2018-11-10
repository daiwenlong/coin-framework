package com.me.coin.framework.mvc.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数的类型为对象
 * @Entity("info.")
 * 参数名以info.为前缀的参数都会封装到该对象中
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Entity {
	
	String value();

}
