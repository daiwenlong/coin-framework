package com.me.coin.framework.dao;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
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
		sql.append(cnd.getWhereSql());
		return new Sql(sql.toString());
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
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
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
			params.add(jsonObject.get(column.getField()));
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
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
		EntityField field = EntityHelper.getId(clazz);
		return getDeleteSql(clazz, Cnd.where()
				.and(field.getColumn(), "=", jsonObject.getString(field.getField())));
	}
	
	/**
	 * 获取删除sql
	 * @param clazz
	 * @param cnd
	 * @return
	 */
	public Sql getDeleteSql(Class<?> clazz,Cnd cnd){
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(EntityHelper.getTableName(clazz));
		sql.append(cnd.getWhereSql());
		return new Sql(sql.toString());
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
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
		List<Object> params = new ArrayList<>();
		int i = 0;
		for(EntityField column: EntityHelper.getColumns(clazz)){
			if(i == 0)
				sql.append(column.getColumn()).append(" = ?");
			else
				sql.append(",").append(column.getColumn()).append(" = ?");
			params.add(jsonObject.get(column.getField()));
			i++;
		} 
		sql.append(" where ").append(EntityHelper.getId(clazz).getColumn()).append(" = ?");
		params.add(jsonObject.get(EntityHelper.getId(clazz).getField()));
		return new Sql(sql.toString(),params);
	}
	


}
