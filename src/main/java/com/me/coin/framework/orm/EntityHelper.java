package com.me.coin.framework.orm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.me.coin.framework.orm.annotation.Column;
import com.me.coin.framework.orm.annotation.Id;
import com.me.coin.framework.orm.annotation.Table;

/**
 * Entity helper
 * @author dwl
 *
 */
public class EntityHelper {
	
	private static Map<Class<?>, Entity> entityCache = new ConcurrentHashMap<>();
	
	
	/**
	 * 获取表名
	 * @param clazz
	 * @return
	 */
    public static String getTableName(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getTableName();
	}
	
	/**
	 * 获取主键
	 * @param clazz
	 * @return
	 */
    public static EntityField getId(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getId();
	}
	
	
	/**
	 * 获取字段映射
	 * @param clazz
	 * @return
	 */
    public static List<EntityField> getColumns(Class<?> clazz){
		if(!entityCache.containsKey(clazz))
			cache(clazz);
		return entityCache.get(clazz).getEntityFields();
	}
    
    
    /**
     * 获取字段属性映射
     * @return
     */
    public static Map<String, String> getMapColumn(Class<?> clazz){
    	List<EntityField> list = getColumns(clazz);
    	Map<String, String> map = new HashMap<>();
    	list.forEach(info->{
    		map.put(info.getColumn(), info.getField());
    	});
    	return map;
    }
	
	//有并发问题，懒得管了
    public static void cache(Class<?> clazz){
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
