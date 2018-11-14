package com.me.coin.framework.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * sql封装
 * @author dwl
 *
 */
public class Sql {
	
	private String value;
	
	private List<Object> params;
	
	public Sql(String value){
		this.value = value;
		this.params = new ArrayList<>();
	}
	
	public Sql(String value, List<Object> params) {
		this.value = value;
		this.params = params;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
	
	
	

}
