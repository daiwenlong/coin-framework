package com.me.coin.framework.mvc;

import java.lang.reflect.Method;

/**
 * action封装
 * @author dwl
 *
 */
public class ActionHandler {
	
	private Class<?> actClazz;
	
	private Method method;
	
	public ActionHandler(Class<?> clazz ,Method method){
		this.actClazz = clazz;
		this.method = method;
	}

	public Class<?> getActClazz() {
		return actClazz;
	}

	public void setActClazz(Class<?> actClazz) {
		this.actClazz = actClazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	

}
