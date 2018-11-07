package com.me.coin.framework.ioc;

public interface CoinIoc {
	
	<T> T getBean(Class<T> clazz);
	
}
