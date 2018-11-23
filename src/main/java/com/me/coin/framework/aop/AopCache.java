package com.me.coin.framework.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.me.coin.framework.aop.annotation.Aspect;
import com.me.coin.framework.ioc.CoinIocCache;
import com.me.coin.framework.util.ClassHelper;


/**
 * 切面上下文
 * 
 * @author dwl
 *
 */
public class AopCache {
	
	private static Map<Class<?>, ProxyChain> cache = new HashMap<>();
	
	
	/**
	 * 生成代理链
	 * @param clazz
	 * @param ioc
	 */
	public static void add(Class<?> clazz,CoinIocCache ioc){
		Aspect aspect = clazz.getAnnotation(Aspect.class);
		List<Class<?>> list = ClassHelper.findClassBypackageName(aspect.pointCut().split(","));
		AopProxy proxy = new AopProxy(ioc.getCoinBean(clazz).getBean());
		for(Class<?> cls:list){
			if(cache.containsKey(cls)){
				cache.get(cls).getProxies().add(proxy);
			}else{
				ProxyChain chain = new ProxyChain();
				chain.getProxies().add(proxy);
				cache.put(cls, chain);
			}
		}
	}
	
	public static Set<Class<?>> getProxyClass(){
		return cache.keySet();
	}
	
	
	public static ProxyChain getChain(Class<?> clazz){
		return cache.get(clazz);
	}

}
