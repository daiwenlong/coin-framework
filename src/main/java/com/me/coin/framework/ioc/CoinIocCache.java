package com.me.coin.framework.ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.me.coin.framework.dao.impl.CoinDao;
import com.me.coin.framework.ioc.annotation.IocBean;
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
	
	public void addCoinBean(Class<?> clazz){
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
