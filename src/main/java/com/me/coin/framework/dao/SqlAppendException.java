package com.me.coin.framework.dao;

public class SqlAppendException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	
	public SqlAppendException(){
		super();
	}
	
	
	public SqlAppendException(String message){
		super(message);
	}

}
