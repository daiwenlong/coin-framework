package com.me.coin.framework.ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.me.coin.framework.aop.AopCache;
import com.me.coin.framework.aop.ProxyChain;
import com.me.coin.framework.aop.ProxyManager;
import com.me.coin.framework.dao.impl.CoinDao;
import com.me.coin.framework.ioc.annotation.IocBean;
import com.me.coin.framework.tx.TransactionProxy;
import com.me.coin.framework.tx.annotation.Service;
import com.me.coin.framework.util.Strings;

/**
 * 存放所有bean的容器
 * @author dwl
 *
 */
public class CoinIocCache {
	
	//存放所有的bean
	private Map<Class<?>, CoinBean> cache = new HashMap<Class<?>, CoinBean>();
	
	public CoinIocCache() {
		CoinBean dao  = new CoinBean("coinDao", new CoinDao());
		dao.setInject(true);
		cache.put(CoinDao.class,dao);
	}
	
	/**
	 * controller类
	 * @param clazz
	 */
	public void addActionBean(Class<?> clazz){
		try {
			String name = Strings.lowerFirst(clazz.getSimpleName());
			cache.put(clazz, new CoinBean(name, clazz.newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * service类通过动态代理生成
	 * @param clazz
	 */
	public void addServiceBean(Class<?> clazz){
		Object bean = TransactionProxy.getProxy(clazz);
		Service service = clazz.getAnnotation(Service.class);
		String name = service.value();
		if(Strings.isEmpty(name))
			name = Strings.lowerFirst(clazz.getSimpleName());
		cache.put(clazz, new CoinBean(name, bean));
	}
	
	
	/**
	 * 普通bean类
	 * @param clazz
	 */
	public void addIocBean(Class<?> clazz){
		try {
			IocBean iocBean = clazz.getAnnotation(IocBean.class);
			String name = iocBean.value();
			if(Strings.isEmpty(name))
				name = Strings.lowerFirst(clazz.getSimpleName());
			cache.put(clazz, new CoinBean(name, clazz.newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * aop切面后的bean
	 * @param clazz
	 */
	public void addAopBean(Class<?> clazz){
		String name = "";
		if(clazz.isAnnotationPresent(IocBean.class))
			name = clazz.getAnnotation(IocBean.class).value();
		if(clazz.isAnnotationPresent(Service.class))
			name = clazz.getAnnotation(Service.class).value();
		ProxyChain chain = AopCache.getChain(clazz);
		Object bean = ProxyManager.getProxy(clazz, chain);
		if(Strings.isEmpty(name))
			name = Strings.lowerFirst(clazz.getSimpleName());
		cache.put(clazz, new CoinBean(name, bean));
	}
	
	
	
	public boolean hasCoinBean(Class<?> clazz){
		return cache.containsKey(clazz);
	}
	
	
	public CoinBean getCoinBean(Class<?> clazz){
		return cache.get(clazz);
	}
	
	
	/**
	 * 根据接口获取所有实现类
	 * @param clazz
	 * @return
	 */
	public List<Class<?>> getInterfaceImpl(Class<?> clazz){
		List<Class<?>> classes = new ArrayList<>();
		cache.forEach((k,v)->{
			if(clazz.isAssignableFrom(k))
				classes.add(k);
		});
		return classes;
	}
	
	
	
	

}
