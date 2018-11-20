package com.me.coin.framework.tx;

import java.lang.reflect.Method;

import com.me.coin.framework.dao.DbHelper;
import com.me.coin.framework.tx.annotation.Transaction;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 事务代理
 * @author dwl
 *
 */
public class TransactionProxy {
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public static <T> T getProxy(Class<T> clazz){
		return (T)new Enhancer().create(clazz, new MethodInterceptor(){
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				//没有@Transaction不进行事务管理
				if(!method.isAnnotationPresent(Transaction.class)){
					result = proxy.invokeSuper(obj, args);
					return result;
				}
				try {
					//开启事务
					DbHelper.beginTransaction();
					// 通过代理类调用父类中的方法
					result = proxy.invokeSuper(obj, args);
					//提交事务
					DbHelper.commitTransaction();
				} catch (Exception e) {
					//回滚
					DbHelper.rollbackTransaction();
				}
				return result;
			}
		});
	}

}
