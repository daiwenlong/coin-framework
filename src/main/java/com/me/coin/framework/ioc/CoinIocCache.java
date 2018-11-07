package com.me.coin.framework.ioc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 存放所有bean的容器
 * @author dwl
 *
 */
public class CoinIocCache {
	
	//存放所有的bean
	private Map<Class<?>, Object> cache = new HashMap<Class<?>, Object>();
	
	//标识所有已经注入的class
	private Set<Class<?>> isInject = new HashSet<Class<?>>();
	
	
	public boolean isInject(Class<?> clazz){
		return isInject.contains(clazz);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T setInject(Class<?> clazz){
		try {
			cache.put(clazz, clazz.newInstance());
			isInject.add(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) cache.get(clazz);
	}
	
	
	public void addClass(Class<?> clazz){
		try {
			cache.put(clazz, clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean hasBean(Class<?> clazz){
		return cache.containsKey(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<?> clazz){
		return (T) cache.get(clazz);
	}

}
