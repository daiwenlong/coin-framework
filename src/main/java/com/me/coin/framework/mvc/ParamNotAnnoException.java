package com.me.coin.framework.mvc;

/**
 * 参数为被@param
 * @author dwl
 *
 */
public class ParamNotAnnoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ParamNotAnnoException(){
		super();
	}
	
	
	public ParamNotAnnoException(String message){
		super(message);
	}

}
