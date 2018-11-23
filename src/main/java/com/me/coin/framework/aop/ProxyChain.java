package com.me.coin.framework.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理链
 * 
 * @author dwl
 *
 */
public class ProxyChain {
	
	private List<AopProxy> proxies = new ArrayList<>();
	
	//保证线程安全
	private ThreadLocal<Integer> currentIndex = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};
	 
	/**
	 * 获取下一个节点
	 * @return
	 */
	public AopProxy next(){
		if(currentIndex.get() >= proxies.size())
			return null;
		AopProxy proxy = proxies.get(currentIndex.get());
		currentIndex.set(currentIndex.get()+1);
		return proxy;
		
	}
	
	/**
	 * 执行代理链
	 * @return
	 */
	public Object handler(Object target, Method method, MethodProxy methodProxy, Object[] args){
		return next().proceed(this, target, method, methodProxy, args);
	}


	public List<AopProxy> getProxies() {
		return proxies;
	}


	public void setProxies(List<AopProxy> proxies) {
		this.proxies = proxies;
	}

	public void removeLocal(){
		this.currentIndex.remove();
	}
	
	
	
}
