package com.me.coin.framework.orm.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 表名
 * 标识在实体映射的表
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Table {
	
	String value();

}
