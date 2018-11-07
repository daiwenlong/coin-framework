package com.me.coin.framework.ioc.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.coin.framework.ioc.BeanNotFoundException;
import com.me.coin.framework.ioc.CoinIoc;
import com.me.coin.framework.ioc.CoinIocCache;
import com.me.coin.framework.ioc.annotation.IocBean;
import com.me.coin.framework.util.ClassHelper;

public class CoinIocImpl implements CoinIoc{
	
	private static Logger logger = LoggerFactory.getLogger(CoinIoc.class);
	
	private static CoinIocCache cache = new CoinIocCache();
	
	//初始化ioc容器
	static {
		logger.info("CoinIoc - 初始化开始...");
		List<Class<?>> classes = ClassHelper.findClassBypackageName("");
		classes.forEach(clazz->{
			if(clazz.isAnnotationPresent(IocBean.class)){
				cache.addClass(clazz);
				logger.info("CoinIoc - 加载类：{}",clazz.getName());
			}
		});
	}

	public <T> T getBean(Class<T> clazz) {
		if(cache.hasBean(clazz))
			throw new BeanNotFoundException("CoinIoc - 无法获取该实例对象-" + clazz);
		return cache.getBean(clazz);
	}

}
