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
 * JDBC����
 */
public class JDBCUtils {
	  
      private static DataSource datasource= null;
      //���г�ʼ������,DataSourceֻ�ܳ�ʼ��һ��  
      static{
    	  datasource = new ComboPooledDataSource("bookstore");
      }
      //��ȡ���ݿ������
      public static Connection getConnection(){
    	  try {
			return datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
      }
      //�����ر���Դ����,�ֿ�������Ϊ�õ��������,�������ֻ����ͬһ��Connection
      public static void release(Connection connection){
    	  
    	  if(connection != null){
    		  try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
      }
      //�ر���Դ����
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
      
   //setParams,��������Ԥ�����������?ռλ����ֵ;
  	public static void setParams(PreparedStatement pstmt, Object[] params) {
  		if (params == null) {
  			return;
  		}// return:ֱ�ӷ��أ�ɶҲ����;
  		try {
  			for (int i = 0; i < params.length; i++) {
  				pstmt.setObject(i + 1, params[i]);
  			}
  		} catch (SQLException e) {// ���쳣,����ȥ
  			e.printStackTrace();
  		}
  	}


	//�������ĸ��·��������Ը������еĻ���sql���;
	public static int executeUpdate(String sql, Object[] params) {
		// 1.��������;�ǽ�����������ʡ�ڴ�;
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; // ��ɾ����Ӱ�������;
 
		try {
			con = JDBCUtils.getConnection();// ���ñ���ķ�����
			pstmt = con.prepareStatement(sql);// ������:Ԥ�������,?
			setParams(pstmt, params);// ��������?�ķ������Ѿ�д����!!!
			count = pstmt.executeUpdate();// 3.ִ��;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(con);
			JDBCUtils.release(null, pstmt);
		}
		return count;
	}

	//ִ�в�ѯ����;
		public static List executeQuery(String sql, Object[] params) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int colCount = 0;
			ArrayList tableList=new ArrayList();//����
			
			try {
				con = JDBCUtils.getConnection();
				pstmt = con.prepareStatement(sql);
				setParams(pstmt, params);
				rs = pstmt.executeQuery();// ִ�в�ѯ�������rs
				ResultSetMetaData rd = rs.getMetaData();// ���Ԫ����
				colCount = rd.getColumnCount();
				while (rs.next()) {
					ArrayList rowList = new ArrayList();//�м���
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
