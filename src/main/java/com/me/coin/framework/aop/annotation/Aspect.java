package com.me.coin.framework.aop.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识切面类
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Aspect {
	
	//切入点
	String pointCut();
	
	//过滤
	String filter() default "";
	
}
