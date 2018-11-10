package com.me.coin.framework.mvc;

import java.util.Map;

/**
 * 返回结果
 * @author dwl
 *
 */
public class Result {
	
	private Map<String, Object> data;
	
	private View view;
	
	private String jspPath;
	
	public Result(Map<String, Object> data,View view){
		this.data = data;
		this.view = view;
	}
	
	public Result(Map<String, Object> data,View view,String jspPath){
		this.data = data;
		this.view = view;
		this.jspPath = jspPath;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}
	
	
	

}
