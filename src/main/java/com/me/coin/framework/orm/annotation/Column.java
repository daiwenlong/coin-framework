package com.me.coin.framework.orm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 属性
 * 标识该属性映射的字段
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	
	String value();

}
