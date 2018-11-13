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
	
	private String path;
	
	public Result(Map<String, Object> data,View view){
		this.data = data;
		this.view = view;
	}
	
	public Result(Map<String, Object> data,View view,String path){
		this.data = data;
		this.view = view;
		this.path = path;
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

	public String getpath() {
		return path;
	}

	public void setpath(String path) {
		this.path = path;
	}
	
	
	

}
