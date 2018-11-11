package com.me.coin.framework.orm;

import java.lang.reflect.Field;

/**
 * 字段映射信息
 * @author dwl
 *
 */
public class EntityField {
	
	private Field field;
	
	private Object value;
	
	public EntityField(){
		
	}
	
	public EntityField(Field field, Object value) {
		this.field = field;
		this.value = value;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	

}
