package com.me.coin.framework.aop;

import java.lang.reflect.Method;


import com.me.coin.framework.aop.annotation.After;
import com.me.coin.framework.aop.annotation.Before;
import com.me.coin.framework.aop.annotation.Throwing;

import net.sf.cglib.proxy.MethodProxy;

/**
 * aop代理
 * 
 * @author dwl
 *
 */
public class AopProxy {
	//切面类
	private Object proxy;
	
	private Method beforeMethod;
	private Method afterMethod;
	private Method throwingMethod;
	

	public AopProxy(Object proxy) {
		this.proxy = proxy;
		setValue(proxy.getClass());
	}

	/**
	 * 执行动作链
	 * @param chain
	 * @param target
	 * @param method
	 * @param methodProxy
	 * @param params
	 * @return
	 */
	public Object proceed(ProxyChain chain, Object target, Method method, MethodProxy methodProxy, Object[] params) {
		Object result = null;
		before(method,params);
		try {
			AopProxy proxy = chain.next();
			if(null != proxy)
				result = proxy.proceed(chain,target,method,methodProxy,params);
			else
				result = methodProxy.invokeSuper(target, params);
			after(method,params);
		} catch (Throwable e) {
			throwing(method,params,e);
		} finally {
			chain.removeLocal();
		}
		
		return result;

	}

	public void before(Method method, Object[] args){
		if(null == beforeMethod)
			return;
		try {
			beforeMethod.setAccessible(true);
			beforeMethod.invoke(proxy, new Object[]{method,args});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void after(Method method, Object[] args){
		if(null == afterMethod)
			return;
		try {
			afterMethod.setAccessible(true);
			afterMethod.invoke(proxy, new Object[]{method,args});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void throwing( Method method, Object[] args,Throwable e) {
		if(null == throwingMethod)
			return;
		try {
			throwingMethod.setAccessible(true);
			throwingMethod.invoke(proxy, new Object[]{method,args});
		} catch (Exception e1) {
			e.printStackTrace();
		}
	}

	
	public Object getProxy() {
		return proxy;
	}

	public void setProxy(Object proxy) {
		this.proxy = proxy;
	}


	public Method getBeforeMethod() {
		return beforeMethod;
	}

	public void setBeforeMethod(Method beforeMethod) {
		this.beforeMethod = beforeMethod;
	}

	public Method getAfterMethod() {
		return afterMethod;
	}

	public void setAfterMethod(Method afterMethod) {
		this.afterMethod = afterMethod;
	}

	public Method getThrowingMethod() {
		return throwingMethod;
	}

	public void setThrowingMethod(Method throwingMethod) {
		this.throwingMethod = throwingMethod;
	}

	private void setValue(Class<?> clazz){
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(Before.class)){
				this.beforeMethod = method;
			}else if(method.isAnnotationPresent(After.class)){
				this.afterMethod = method;
			}else if(method.isAnnotationPresent(Throwing.class)){
				this.throwingMethod = method;
			}
		}
	}

}
