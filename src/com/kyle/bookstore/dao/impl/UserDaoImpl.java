package com.kyle.bookstore.dao.impl;

import com.kyle.bookstore.dao.UserDao;
import com.kyle.bookstore.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public void getUser(User user) {
		String sql = "INSERT INTO user VALUES(?,?,?,?)";
		update(sql, user.getUser_id(),user.getUser_name(),user.getPassword(),user.getAccount_id());
	}
    
}
