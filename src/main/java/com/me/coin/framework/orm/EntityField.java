package com.me.coin.framework.orm;


/**
 * 字段映射信息
 * @author dwl
 *
 */
public class EntityField {
	
	private String field;
	
	private String column;
	
	public EntityField(){
		
	}
	
	public EntityField(String field, String column) {
		this.field = field;
		this.column = column;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

}
