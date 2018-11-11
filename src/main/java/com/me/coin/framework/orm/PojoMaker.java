package com.me.coin.framework.orm;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据映射的一些操作
 * @author dwl
 *
 */
public class PojoMaker {
	
	
	/**
	 * 将数据封装成对象
	 * @param clazz
	 * @param entity
	 * @return
	 */
	public static <T> T getPojo(Class<T> clazz,Entity entity){
		List<EntityField> list = entity.getEntityFields();
		JSONObject object = new JSONObject();
		list.forEach(info->{
			object.put(info.getField().getName(), info.getValue());
		});
		return JSONObject.toJavaObject(object, clazz);
	}

}
