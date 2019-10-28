package com.kyle.bookstore.service;

import com.kyle.bookstore.dao.UserDao;
import com.kyle.bookstore.dao.impl.UserDaoImpl;
import com.kyle.bookstore.domain.User;

public class UserService {

	private UserDao userDao = (UserDao) new UserDaoImpl();
	
	public void register(User user){
		userDao.getUser(user);
	}
	

}
