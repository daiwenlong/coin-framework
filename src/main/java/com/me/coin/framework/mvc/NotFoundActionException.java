package com.me.coin.framework.mvc;

public class NotFoundActionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NotFoundActionException(){
		super();
	}
	
	
	public NotFoundActionException(String message){
		super(message);
	}
	
	public NotFoundActionException(String message,Throwable e){
		super(message ,e);
	}

}
