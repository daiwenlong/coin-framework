package com.me.coin.framework.mvc;

import java.lang.reflect.Method;

/**
 * action封装
 * @author dwl
 *
 */
public class ActionHandler {
	
	private Class<?> ActClazz;
	
	private Method method;
	
	public ActionHandler(Class<?> clazz ,Method method){
		this.ActClazz = clazz;
		this.method = method;
	}

	public Class<?> getActClazz() {
		return ActClazz;
	}

	public void setActClazz(Class<?> actClazz) {
		ActClazz = actClazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	

}
