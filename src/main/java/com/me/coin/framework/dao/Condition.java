package com.me.coin.framework.dao;

/**
 * 单个条件封装
 * @author dwl
 *
 */
public class Condition {
	
	private String column;
	
	private String op;
	
	private String value;
	
	private Op type;
	
	public Condition(String column, String op, String value, Op type) {
		this.column = column;
		this.op = op;
		this.value = value;
		this.type = type;
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public String getOp() {
		return op;
	}


	public void setOp(String op) {
		this.op = op;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Op getType() {
		return type;
	}


	public void setType(Op type) {
		this.type = type;
	}
	
	

}
