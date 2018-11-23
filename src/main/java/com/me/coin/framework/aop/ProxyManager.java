package com.me.coin.framework.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * aop
 * 
 * 根据代理链生成代理后的对象
 * 
 * @author dwl
 *
 */
public class ProxyManager {
	
	
	/**
	 * @param clazz 
	 * 
	 *   被横切的类
	 * @param chain
	 * 
	 *   代理链
	 * @return
	 * 
	 *   生成一个代理实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> clazz,ProxyChain chain){
		
		return (T) Enhancer.create(clazz, new MethodInterceptor(){
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return chain.handler(obj, method, proxy, args);
			}
		});
	}

}
