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
	int insert(Object obj);
	
	
	
	/**
	 * 新增 
	 * @param object
	 * @param clazz Long.class
	 * @return 自增主键
	 */
	<T> T insert(Object obj,Class<T> clazz);
	
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
	<T> T fetch(Class<T> clazz,long id);
	
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
	int delete(Class<?> clazz,long id);
	
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
	
	/**
	 * 删除
	 * @param obj
	 * @return
	 */
	int delete(Object obj);
	
	/**
	 * 查询数量
	 * @param clazz
	 * @param cnd
	 * @return
	 */
	long count(Class<?> clazz,Cnd cnd);
	
	
	

}
