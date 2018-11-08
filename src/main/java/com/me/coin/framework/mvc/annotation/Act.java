package com.me.coin.framework.mvc.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 控制层标识
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Act {

}
