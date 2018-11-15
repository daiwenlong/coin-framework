package com.me.coin.framework.dao;

/**
 * 单个条件封装
 * @author dwl
 *
 */
public class Condition {
	
	private String column;
	
	private String op;
	
	private Op type;
	
	public Condition(String column, String op, Op type) {
		this.column = column;
		this.op = op;
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


	public Op getType() {
		return type;
	}


	public void setType(Op type) {
		this.type = type;
	}
	
	

}
