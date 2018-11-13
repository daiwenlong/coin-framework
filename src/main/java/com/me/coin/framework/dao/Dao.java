package com.me.coin.framework.dao;

import java.util.List;


/**
 * 封装数据库操作
 * @author dwl
 *
 */
public interface Dao {
	
	
	/**
	 * 新增
	 * @param obj
	 * @return
	 */
	Object insert(Object obj);
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	int update(Object obj);
	
	/**
	 * 按条件查询
	 * @param clazz
	 * @param cnd
	 * @return
	 */
	<T> List<T> query(Class<T> clazz,Cnd cnd);
	
	/**
	 * 按条件分页查询
	 * @param clazz
	 * @param cnd
	 * @param pager
	 * @return
	 */
	<T> List<T> query(Class<T> clazz,Cnd cnd,Pager pager);
	
	/**
	 * 按住键查询一条
	 * @param clazz
	 * @param id
	 * @return
	 */
	<T> T fetch(Class<T> clazz,Integer id);
	
	/**
	 * 按住键查询一条
	 * @param clazz
	 * @param id
	 * @return
	 */
	<T> T fetch(Class<T> clazz,String id);
	
	/**
	 * 按主键删除
	 * @param clazz
	 * @param id
	 * @return
	 */
	int delete(Class<?> clazz,Integer id);
	
	/**
	 * 按主键删除
	 * @param clazz
	 * @param id
	 * @return
	 */
	int delete(Class<?> clazz,String id);
	
	/**
	 * 按条件删除
	 * @param clazz
	 * @param cnd
	 * @return
	 */
	int delete(Class<?> clazz,Cnd cnd);
	

}
