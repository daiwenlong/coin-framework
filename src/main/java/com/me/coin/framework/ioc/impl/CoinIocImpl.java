package com.me.coin.framework.ioc.impl;


import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.coin.framework.Constants;
import com.me.coin.framework.ioc.BeanNotFoundException;
import com.me.coin.framework.ioc.CoinBean;
import com.me.coin.framework.ioc.CoinIoc;
import com.me.coin.framework.ioc.CoinIocCache;
import com.me.coin.framework.ioc.annotation.Inject;
import com.me.coin.framework.ioc.annotation.IocBean;
import com.me.coin.framework.mvc.annotation.Act;
import com.me.coin.framework.tx.annotation.Service;
import com.me.coin.framework.util.ClassHelper;
import com.me.coin.framework.util.PropertyUtils;
import com.me.coin.framework.util.Strings;


public class CoinIocImpl implements CoinIoc{
	
	private static Logger logger = LoggerFactory.getLogger(CoinIoc.class);
	
	private static CoinIocCache cache = new CoinIocCache();
	
	//初始化ioc容器 controller service 以及一些组件都需要交给ioc
	static {
		logger.info("CoinIoc - 初始化开始...");
		List<Class<?>> classes = ClassHelper.findClassBypackageName(PropertyUtils.getPropertyArray((Constants.IOC_PACKAGE)));
		classes.forEach(clazz->{
			if(clazz.isAnnotationPresent(Act.class)){
				cache.addActionBean(clazz);;
				logger.info("CoinIoc - 加载controller类：{}",clazz.getName());
			}
			if(clazz.isAnnotationPresent(Service.class)){
				cache.addServiceBean(clazz);
				logger.info("CoinIoc - 加载service类：{}",clazz.getName());
			}
			if(clazz.isAnnotationPresent(IocBean.class)){
				cache.addIocBean(clazz);
				logger.info("CoinIoc - 加载组件bean类：{}",clazz.getName());
			}
		});
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		if(!cache.hasCoinBean(clazz))
			throw new BeanNotFoundException("CoinIoc - 无法获取该实例对象-" + clazz.getName());
		CoinBean coinBean = cache.getCoinBean(clazz);
		if(!coinBean.isInject())
			return injectBean(clazz);
		return (T) coinBean.getBean();
			
		
	}
	
	
	@SuppressWarnings("unchecked")
	private <T> T injectBean(Class<?> clazz){
		synchronized (CoinIoc.class) {
			CoinBean coinBean = cache.getCoinBean(clazz);
			if(coinBean.isInject())
				return (T) coinBean.getBean();
			setFeild(clazz, coinBean);
		}
		return (T) cache.getCoinBean(clazz).getBean();
	}
	
	private void setFeild(Class<?> clazz, CoinBean coinBean){
		 Field[] fields = clazz.getDeclaredFields();
		 if(null != fields){
			 try {
				 for(Field field:fields){
					 if(field.isAnnotationPresent(Inject.class)){
						 Inject inject = field.getAnnotation(Inject.class);
						 Class<?> classType = field.getType();
						 String implName = inject.value();
						 //如果是接口
						 if(classType.isInterface()){
							 List<Class<?>> impl = cache.getInterfaceImpl(classType);
							 if(impl.size() == 0)
								 throw new RuntimeException("CoinIoc - 无法找到"+classType.getName()+"的实现类");
							 Class<?> classImpl = impl.get(0);
							 //指定了实现类
							 if(!Strings.isEmpty(implName)){
								 for(Class<?> cls:impl){
									 if(implName.equals(cache.getCoinBean(cls).getName())){
										 classImpl = cls;
										 break;
									 }
								 }
							 }
							 setFeild(classImpl, cache.getCoinBean(classImpl));
							 field.setAccessible(true);
							 field.set(coinBean.getBean(), cache.getCoinBean(classImpl).getBean());
						 }else{
							 if(null == cache.getCoinBean(classType))
								throw new RuntimeException("CoinIoc -  无法找到"+classType.getName());
							 setFeild(classType, cache.getCoinBean(classType));
							 field.setAccessible(true);
							 field.set(coinBean.getBean(), cache.getCoinBean(classType).getBean());
						 }
					 }
				 }
			} catch (Exception e) {
				  throw new RuntimeException("CoinIoc - "+clazz.getName()+"注入失败");
			}
		 }
		 //注入完成
		 coinBean.setInject(true);
	}

}
