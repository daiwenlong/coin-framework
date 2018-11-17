package com.me.coin.framework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.coin.framework.dao.Cnd;
import com.me.coin.framework.dao.Dao;
import com.me.coin.framework.dao.DataSourceMaker;
import com.me.coin.framework.dao.Pager;
import com.me.coin.framework.dao.Sql;
import com.me.coin.framework.dao.SqlMaker;
import com.me.coin.framework.orm.EntityHelper;

public class CoinDao implements Dao{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SqlMaker sqlMaker;
	
	private QueryRunner queryRunner;
	
	public CoinDao() {
		sqlMaker = new SqlMaker();
		queryRunner = new QueryRunner(DataSourceMaker.getDataSource());
	}
	
	

	@Override
	public int insert(Object obj) {
		Sql sql = sqlMaker.getInsertSql(obj);
		printSql(sql);
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
		printSql(sql);
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
		printSql(sql);
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
		printSql(sql);
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
	public <T> T fetch(Class<T> clazz, long id) {
		Sql sql = sqlMaker.getSelectSql(clazz, id);
		printSql(sql);
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
		printSql(sql);
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
	public int delete(Class<?> clazz, long id) {
		Sql sql = sqlMaker.getDeleteSql(clazz, id);
		printSql(sql);
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
		printSql(sql);
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
		printSql(sql);
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
		printSql(sql);
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
		printSql(sql);
		long result = 0;
		try {
			result =  queryRunner.query(sql.getValue(),
					new ScalarHandler<Long>(),sql.getParams().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void printSql(Sql sql){
		logger.info("sql: {} ; params: {}",new Object[]{sql.getValue(),sql.getParams().toArray()});
	}
	
	

}
