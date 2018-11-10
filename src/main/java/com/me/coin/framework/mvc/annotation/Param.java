package com.me.coin.framework.mvc.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数映射
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Param {
	
	String value();

}
