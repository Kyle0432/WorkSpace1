package com.kyle.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.kyle.bookstore.dao.ShoppingCart;
import com.kyle.bookstore.db.JDBCUtils;
import com.kyle.bookstore.domain.ShoppingCartItem;

public class ShoppingCartImpl extends BaseDao<ShoppingCartItem> implements ShoppingCart{

	
	
	
	
	
	
	
	
	
   /* Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	@Override
	public int addCartItem(ShoppingCartItem shoppingCartItem) {
		String sql = "INSERT INTO shopcartitem VALUES(?,?,?,?,?)";
		int result = -1;
		
		try {
			connection = JDBCUtils.getConnection();
			Object [] params = {shoppingCartItem.getUser_id(),shoppingCartItem.getBook_id(),shoppingCartItem.getTitle(),shoppingCartItem.getPrice(),shoppingCartItem.getQuantity()};
			pstmt =  connection.prepareStatement(sql);
			JDBCUtils.setParams(pstmt, params);
			result = JDBCUtils.executeUpdate(sql, params);
		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt);
			JDBCUtils.release(connection);
		}
		return result;
	}

	@Override
	public boolean isEmpty(int user_id, Integer book_id) {
		String sql = "SELECT user_id,book_id FROM shopcartitem WHERE user_id = ? AND book_id = ?";
		
		boolean result = false;
		
		try {
			connection = JDBCUtils.getConnection();
			pstmt = connection.prepareStatement(sql);
			Object [] params = {user_id,book_id};
			JDBCUtils.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
             e.printStackTrace();
		}finally{
			 JDBCUtils.release(connection);
			 JDBCUtils.release(null, pstmt);
		}
		return result;
	}

	@Override
	public Map<Integer, ShoppingCartItem> getShoppingCartItem(int user_id) {
		String sql = "SELECT * FROM shopcartitem WHERE user_id = ?";
		
		Map<Integer,ShoppingCartItem> shoppingCartItems = new HashMap<>();
		
		try {
			connection = JDBCUtils.getConnection();
			pstmt = connection.prepareStatement(sql);
			Object [] params = {user_id};
			JDBCUtils.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ShoppingCartItem item = new ShoppingCartItem();
				item.setUser_id(user_id);
				int book_id = rs.getInt("book_id");
				item.setBook_id(book_id);
				item.setTitle(rs.getString("title"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));
				
				shoppingCartItems.put(book_id,item);
			}
		} catch (Exception e) {
              e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
			JDBCUtils.release(null, pstmt);
		}
		
		return shoppingCartItems;
	}

	@Override
	public ShoppingCartItem findCartItemById(int user_id, Integer book_id) {
		String sql = "SELECT * FROM shopcartitem WHERE user_id = ? AND book_id = ?";
		ResultSet rs = null;
		ShoppingCartItem item = null;
		
		try {
			connection = JDBCUtils.getConnection();
			pstmt = connection.prepareStatement(sql);
			Object [] params = {user_id,};
			JDBCUtils.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if(rs.next()){
				item = new ShoppingCartItem();
				item.setUser_id(rs.getInt("user_id"));
				item.setTitle(rs.getString("title"));
				item.setBook_id(rs.getInt("book_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPrice(rs.getDouble("price"));
			}
		} catch (Exception e) {
                e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
			JDBCUtils.release(rs, pstmt);
		}
		return item;
	}

	@Override
	public int updateShoppingCartItemQuantity(ShoppingCartItem shoppingCartItem) {
		String sql = "UPDATE shopcartitem SET quantity = ? WHERE user_id = ? AND book_id = ?";
		int result = -1;
		try {
			 connection = JDBCUtils.getConnection();
			 pstmt = connection.prepareStatement(sql);
			 Object [] params = {shoppingCartItem.getQuantity(),shoppingCartItem.getUser_id(),shoppingCartItem.getBook_id()};
			 JDBCUtils.setParams(pstmt, params);
			 result = pstmt.executeUpdate();
		} catch (Exception e) {
             e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
			JDBCUtils.release(null, pstmt);
		}
		return result;
	}*/

}
