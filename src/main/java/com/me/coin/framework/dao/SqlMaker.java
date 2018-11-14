package com.me.coin.framework.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;
import com.me.coin.framework.orm.Entity;
import com.me.coin.framework.orm.EntityField;
import com.me.coin.framework.orm.annotation.Column;
import com.me.coin.framework.orm.annotation.Id;
import com.me.coin.framework.orm.annotation.Table;

/**
 * sql拼装
 * @author dwl
 *
 */
public class SqlMaker {
	
	private static Map<Class<?>, Entity> entityCache = new ConcurrentHashMap<>();
	
	
	public Sql getQuerySql(Class<?> clazz,Cnd cnd,Pager pager){
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getTableName(clazz));
		sql.append(cnd.getWhereSql());
		return new Sql(sql.toString());
	}
	
	
	
	public Sql getInsertSql(Object obj){
		Class<?> clazz = obj.getClass();
		StringBuilder sql = new StringBuilder("insert into ");
		StringBuilder values = new StringBuilder(" values (");
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
		List<Object> params = new ArrayList<>();
		sql.append(getTableName(clazz)).append("(");
		int i = 0;
		for(EntityField column: getColumns(clazz)){
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
	
	
	public Sql getDeleteSql(Object obj){
		Class<?> clazz = obj.getClass();
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
		EntityField field = getId(clazz);
		return getDeleteSql(clazz, Cnd.where()
				.and(field.getColumn(), "=", jsonObject.getString(field.getField())));
	}
	
	public Sql getDeleteSql(Class<?> clazz,Cnd cnd){
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(getTableName(clazz));
		sql.append(cnd.getWhereSql());
		return new Sql(sql.toString());
	}
	
	public Sql getUpdateSql(Object obj){
		Class<?> clazz = obj.getClass();
		StringBuilder sql = new StringBuilder("update ");
		sql.append(getTableName(clazz)).append(" set ");
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
		List<Object> params = new ArrayList<>();
		int i = 0;
		for(EntityField column: getColumns(clazz)){
			if(i == 0)
				sql.append(column.getColumn()).append(" = ?");
			else
				sql.append(",").append(column.getColumn()).append(" = ?");
			params.add(jsonObject.get(column.getField()));
			i++;
		} 
		sql.append(" where ").append(getId(clazz).getColumn()).append(" = ?");
		params.add(jsonObject.get(getId(clazz).getField()));
		return new Sql(sql.toString(),params);
	}
	
	
	/**
	 * 获取表名
	 * @param clazz
	 * @return
	 */
	private String getTableName(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getTableName();
	}
	
	/**
	 * 获取主键
	 * @param clazz
	 * @return
	 */
	private EntityField getId(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getId();
	}
	
	
	/**
	 * 获取字段映射
	 * @param clazz
	 * @return
	 */
	private List<EntityField> getColumns(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getEntityFields();
	}
	
	//有并发问题，懒得管了
	private void cache(Class<?> clazz){
		List<EntityField> list = new ArrayList<>();
		Entity entity = new Entity();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(Column.class))
				list.add(new EntityField(field.getName(), field.getAnnotation(Column.class).value()));
			if(field.isAnnotationPresent(Id.class))
				entity.setId(new EntityField(field.getName(), field.getAnnotation(Column.class).value()));
		}
		entity.setEntityFields(list);
		entity.setTableName(clazz.getAnnotation(Table.class).value());
		entityCache.put(clazz, entity);
	}

}
