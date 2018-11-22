package com.me.coin.framework.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 后置通知
 * 
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface After {

}
