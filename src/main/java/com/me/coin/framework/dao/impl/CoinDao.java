package com.me.coin.framework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.me.coin.framework.dao.Cnd;
import com.me.coin.framework.dao.Dao;
import com.me.coin.framework.dao.DataSourceMaker;
import com.me.coin.framework.dao.Pager;
import com.me.coin.framework.dao.Sql;
import com.me.coin.framework.dao.SqlMaker;
import com.me.coin.framework.orm.EntityHelper;

public class CoinDao implements Dao{
	
	
	private SqlMaker sqlMaker;
	
	private QueryRunner queryRunner;
	
	public CoinDao() {
		sqlMaker = new SqlMaker();
		queryRunner = new QueryRunner(DataSourceMaker.getDataSource());
	}
	
	

	@Override
	public int insert(Object obj) {
		Sql sql = sqlMaker.getInsertSql(obj);
		int result = 0;
		try {
			result = queryRunner.execute(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Object obj) {
		Sql sql = sqlMaker.getUpdateSql(obj);
		int result = 0;
		try {
			result = queryRunner.update(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T> List<T> query(Class<T> clazz, Cnd cnd) {
		Sql sql = sqlMaker.getSelectSql(clazz, cnd, null);
		List<T> list = null;
		try {
			list = queryRunner.query(sql.getValue(), new BeanListHandler<T>(clazz,
					new BasicRowProcessor(new BeanProcessor(EntityHelper.getMapColumn(clazz)))),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> List<T> query(Class<T> clazz, Cnd cnd, Pager pager) {
		Sql sql = sqlMaker.getSelectSql(clazz, cnd, pager);
		List<T> list = null;
		try {
			list = queryRunner.query(sql.getValue(), new BeanListHandler<T>(clazz,
					new BasicRowProcessor(new BeanProcessor(EntityHelper.getMapColumn(clazz)))),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <T> T fetch(Class<T> clazz, Integer id) {
		Sql sql = sqlMaker.getSelectSql(clazz, id);
		T result = null;
		try {
			result = queryRunner.query(sql.getValue(), new BeanHandler<T>(clazz,
					new BasicRowProcessor(new BeanProcessor(EntityHelper.getMapColumn(clazz)))),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T> T fetch(Class<T> clazz, String id) {
		Sql sql = sqlMaker.getSelectSql(clazz, id);
		T result = null;
		try {
			result = queryRunner.query(sql.getValue(), new BeanHandler<T>(clazz,
					new BasicRowProcessor(new BeanProcessor(EntityHelper.getMapColumn(clazz)))),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Class<?> clazz, Integer id) {
		Sql sql = sqlMaker.getDeleteSql(clazz, id);
		int result = 0;
		try {
			result = queryRunner.execute(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Class<?> clazz, String id) {
		Sql sql = sqlMaker.getDeleteSql(clazz, id);
		int result = 0;
		try {
			result = queryRunner.execute(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Class<?> clazz, Cnd cnd) {
		Sql sql = sqlMaker.getDeleteSql(clazz, cnd);
		int result = 0;
		try {
			result = queryRunner.execute(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Object obj) {
		Sql sql = sqlMaker.getDeleteSql(obj);
		int result = 0;
		try {
			result = queryRunner.execute(sql.getValue(), sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long count(Class<?> clazz, Cnd cnd) {
		Sql sql = sqlMaker.getCountSql(clazz, cnd);
		long result = 0;
		try {
			result =  queryRunner.query(sql.getValue(),
					new ScalarHandler<Long>(),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
