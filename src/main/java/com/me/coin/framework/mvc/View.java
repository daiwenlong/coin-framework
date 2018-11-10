package com.me.coin.framework.mvc;

/**
 * 返回视图类型
 * @author dwl
 *
 */
public enum View {
	
	Json("json"),jsp("jsp");
	
	
	private String name;
	
	
	private View(String name){
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
