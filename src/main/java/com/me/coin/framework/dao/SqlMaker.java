package com.me.coin.framework.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.me.coin.framework.orm.EntityField;
import com.me.coin.framework.orm.EntityHelper;

/**
 * sql拼装
 * @author dwl
 *
 */
public class SqlMaker {
	
	
	/**
	 * 获取查询sql
	 * @param clazz
	 * @param cnd
	 * @param pager
	 * @return
	 */
	public Sql getQuerySql(Class<?> clazz,Cnd cnd,Pager pager){
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(EntityHelper.getTableName(clazz));
		Sql where = cnd.getSql();
		sql.append(where.getValue());
		return new Sql(sql.toString(),where.getParams());
	}
	
	
	
	/**
	 * 获取插入sql
	 * @param obj
	 * @return
	 */
	public Sql getInsertSql(Object obj){
		Class<?> clazz = obj.getClass();
		StringBuilder sql = new StringBuilder("insert into ");
		StringBuilder values = new StringBuilder(" values (");
		List<Object> params = new ArrayList<>();
		sql.append(EntityHelper.getTableName(clazz)).append("(");
		int i = 0;
		for(EntityField column: EntityHelper.getColumns(clazz)){
			if(i == 0){
				sql.append(column.getColumn());
				values.append("?");
			}else{
				sql.append(",").append(column.getColumn());
				values.append(",").append("?");
			}
			params.add(getFieldValue(obj, column));
			i++;
		}
		sql.append(")");
		values.append(")");
		return new Sql(sql.toString()+values.toString(),params);
	}
	
	
	/**
	 * 获取删除sql
	 * @param obj
	 * @return
	 */
	public Sql getDeleteSql(Object obj){
		Class<?> clazz = obj.getClass();
		EntityField field = EntityHelper.getId(clazz);
		return getDeleteSql(clazz, Cnd.where()
				.and(field.getColumn(), "=", getFieldValue(obj, field)));
	}
	
	/**
	 * 获取删除sql
	 * @param clazz
	 * @param cnd
	 * @return
	 */
	public Sql getDeleteSql(Class<?> clazz,Cnd cnd){
		Sql where = cnd.getSql();
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(EntityHelper.getTableName(clazz));
		sql.append(where.getValue());
		return new Sql(sql.toString(),where.getParams());
	}
	
	/**
	 * 获取更新sql
	 * @param obj
	 * @return
	 */
	public Sql getUpdateSql(Object obj){
		Class<?> clazz = obj.getClass();
		StringBuilder sql = new StringBuilder("update ");
		sql.append(EntityHelper.getTableName(clazz)).append(" set ");
		List<Object> params = new ArrayList<>();
		int i = 0;
		for(EntityField column: EntityHelper.getColumns(clazz)){
			if(i == 0)
				sql.append(column.getColumn()).append("= ?");
			else
				sql.append(",").append(column.getColumn()).append("= ?");
			params.add(getFieldValue(obj, column));
			i++;
		} 
		sql.append(" where ").append(EntityHelper.getId(clazz).getColumn()).append("= ?");
		params.add(getFieldValue(obj, EntityHelper.getId(clazz)));
		return new Sql(sql.toString(),params);
	}
	
	
	/**
	 * 获取属性值
	 * @param obj
	 * @param field
	 * @return
	 */
	private Object getFieldValue(Object obj,EntityField field){
		PropertyDescriptor pd;
		Object value = null;
		try {
			pd = new PropertyDescriptor(field.getField(),obj.getClass());
			Method getMethod = pd.getReadMethod();  
			value = getMethod.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	


}
