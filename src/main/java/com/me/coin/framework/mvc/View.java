package com.me.coin.framework.mvc;

/**
 * 返回视图类型
 * @author dwl
 *
 */
public enum View {
	
	Json("json","返回json"),
	Jsp("jsp","返回jsp"),
	Redirect("redirect","重定向");
	
	private String type;
	
	
	private String name;
	
	private View(String type, String name) {
		this.type = type;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	

}
