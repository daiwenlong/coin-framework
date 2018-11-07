package com.me.coin.framework.ioc.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识该类交给ioc管理
 * value 为该类在ioc中的唯一标识，默认为该类名称第一字母小写
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE })
public @interface IocBean {

	String value() default "";
}
