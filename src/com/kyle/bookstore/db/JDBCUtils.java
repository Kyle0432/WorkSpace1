package com.kyle.bookstore.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/*
 * JDBC工具
 */
public class JDBCUtils {
	  
      private static DataSource datasource= null;
      //进行初始化操作,DataSource只能初始化一次  
      static{
    	  datasource = new ComboPooledDataSource("bookstore");
      }
      //获取数据库的连接
      public static Connection getConnection(){
    	  try {
			return datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
      }
      //单独关闭资源操作,分开来是因为用到事务操作,事务操作只能用同一个Connection
      public static void release(Connection connection){
    	  
    	  if(connection != null){
    		  try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
      }
      //关闭资源操作
      public static void release(ResultSet rs ,Statement stmt){
    	  
    	  if(rs != null){
    		  try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
    	  if(stmt != null){
    		  try {
				stmt.cancel();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
      }
      
   //setParams,用来设置预编译语句对象的?占位符的值;
  	public static void setParams(PreparedStatement pstmt, Object[] params) {
  		if (params == null) {
  			return;
  		}// return:直接返回，啥也不做;
  		try {
  			for (int i = 0; i < params.length; i++) {
  				pstmt.setObject(i + 1, params[i]);
  			}
  		} catch (SQLException e) {// 有异常,加上去
  			e.printStackTrace();
  		}
  	}


	//做公共的更新方法，可以更新所有的基本sql语句;
	public static int executeUpdate(String sql, Object[] params) {
		// 1.声明对象;是将来工作当中省内存;
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; // 增删改受影响的行数;
 
		try {
			con = JDBCUtils.getConnection();// 调用本类的方法；
			pstmt = con.prepareStatement(sql);// 建对象:预编译对象,?
			setParams(pstmt, params);// 调用设置?的方法，已经写过了!!!
			count = pstmt.executeUpdate();// 3.执行;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(con);
			JDBCUtils.release(null, pstmt);
		}
		return count;
	}

	//执行查询方法;
		public static List executeQuery(String sql, Object[] params) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int colCount = 0;
			ArrayList tableList=new ArrayList();//表集合
			
			try {
				con = JDBCUtils.getConnection();
				pstmt = con.prepareStatement(sql);
				setParams(pstmt, params);
				rs = pstmt.executeQuery();// 执行查询，结果给rs
				ResultSetMetaData rd = rs.getMetaData();// 获得元数据
				colCount = rd.getColumnCount();
				while (rs.next()) {
					ArrayList rowList = new ArrayList();//行集合
					for (int i = 1; i <= colCount; i++) {
						rowList.add(rs.getString(i));
					}
					tableList.add(rowList);				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JDBCUtils.release(con);
				JDBCUtils.release(rs, pstmt);
			}
			return tableList;
		}

}
