package com.me.coin.framework.ioc;

public class BeanNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public BeanNotFoundException() {
		super();
	}
	
	public BeanNotFoundException(String message){
		super(message);
	}
    
	public BeanNotFoundException(String message, Throwable cause) {
	    super(message, cause);
	}
}
