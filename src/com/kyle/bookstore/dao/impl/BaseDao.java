package com.kyle.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kyle.bookstore.dao.Dao;
import com.kyle.bookstore.db.JDBCUtils;
import com.kyle.bookstore.utils.ReflectionUtils;
import java.sql.PreparedStatement;

public class BaseDao<T> implements Dao<T>{
	
	private static final HttpServletRequest request = null;
	private static final HttpServletResponse response = null;
	
	private QueryRunner query = new QueryRunner();
	
	private Class<T> clazz;
	
	public BaseDao() {
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void getlogin(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			connection = JDBCUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0 ; i < args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			rs = preparedStatement.executeQuery();
			if(rs.next()){
			String userName = rs.getString("user_name");
		  req.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(req, resp);
			}else{
		  resp.sendRedirect(req.getContextPath()+"/error-2.jsp");
			}
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
			JDBCUtils.release(rs, preparedStatement);
		}
	}
	@Override
	public void update(String sql, Object... agrs) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			query.update(connection, sql, agrs);
		} catch (Exception e) {
          e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
	}

	@Override
	public T get(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return query.query(connection, sql, new BeanHandler<T>(clazz),args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			JDBCUtils.release(connection);
		}
		return null;
	}

	@Override
	public List<T> getForList(String sql, Object ... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return query.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
		return null;
	}

	@Override
	public <E> E getForValue(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return (E) query.query(connection, sql, new ScalarHandler(),args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
		return null;
	}
    //批量更新操作
	@Override
	public void batch(String sql, Object[]... params) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			query.batch(connection, sql, params);
		} catch (Exception e) {
          e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
	}


}
