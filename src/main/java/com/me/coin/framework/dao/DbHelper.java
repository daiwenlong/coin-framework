package com.me.coin.framework.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 提供数据库的一些操作
 * @author dwl
 *
 */
public class DbHelper {

	private static ThreadLocal<Connection> localConn = new ThreadLocal<Connection>();

	private static DataSource dataSource = DataSourceMaker.getInstance().getDataSource();

	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = localConn.get();
			if (conn == null) {
				conn = dataSource.getConnection();
				if (conn != null) {
					localConn.set(conn);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return conn;
	}

	
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				localConn.set(conn);
			}
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				localConn.remove();
			}
		}
	}

	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				localConn.remove();
			}
		}
	}

}
