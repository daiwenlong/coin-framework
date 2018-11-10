package com.me.coin.framework.mvc;

import com.me.coin.framework.ioc.CoinIoc;
import com.me.coin.framework.ioc.impl.CoinIocImpl;

public abstract class Mvcs {
	
	private static CoinIoc ioc;
	
	public static void init(){
		if(null == ioc)
			ioc = new CoinIocImpl();
	}
	
	public static CoinIoc getIoc(){
		return ioc;
	}
	

}
