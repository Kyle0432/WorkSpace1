package com.kyle.bookstore.service;

import java.util.List;

import com.kyle.bookstore.dao.BookDao;
import com.kyle.bookstore.dao.impl.BookDaoImpl;
import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public class BookService {
   //进行实例化
	private BookDao bookDao = new BookDaoImpl();
   //通过Dao获取Page<Book>对象
	public Page<Book> getPage(CriteriaBook criteriabook){
		return bookDao.getPage(criteriabook);
	}
	//获取Book对象
	public Book getBook(int book_id ){
		return bookDao.getBook(book_id);
	}
	//获取List<Book>集合
	public Book getBook(LikeQueryBook likeQueryBook){
		return bookDao.getForListWithLikeQueryBook(likeQueryBook);
	}
/*	public Book getBook(LikeQueryBook likeQueryBook){
		return bookDao.getForListWithLikeQueryBook(likeQueryBook);
	}*/
}
