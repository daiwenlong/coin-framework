package com.me.coin.framework.ioc;


/**
 * 封装bean
 * @author dwl
 *
 */
public class CoinBean {
	
	/**
	 * 是否完成注入
	 */
	private boolean isInject;
	
	/**
	 * bean的名称
	 */
	private String name;
	
	/**
	 * 实例对象
	 */
	private Object bean;
	
	public CoinBean(){}
	
	
	public CoinBean(Object bean){
		this.bean = bean;
	}
	
	public CoinBean(String name, Object bean){
		this.name = name;
		this.bean = bean;
	}


	public boolean isInject() {
		return isInject;
	}


	public void setInject(boolean isInject) {
		this.isInject = isInject;
	}


	public Object getBean() {
		return bean;
	}


	public void setBean(Object bean) {
		this.bean = bean;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
