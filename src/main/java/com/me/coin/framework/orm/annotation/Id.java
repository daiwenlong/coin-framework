package com.me.coin.framework.orm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 主键
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Id {

}
