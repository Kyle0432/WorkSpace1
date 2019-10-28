package com.kyle.bookstore.service;

import java.util.List;

import com.kyle.bookstore.dao.BookDao;
import com.kyle.bookstore.dao.impl.BookDaoImpl;
import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public class BookService {
   //����ʵ����
	private BookDao bookDao = new BookDaoImpl();
   //ͨ��Dao��ȡPage<Book>����
	public Page<Book> getPage(CriteriaBook criteriabook){
		return bookDao.getPage(criteriabook);
	}
	//��ȡBook����
	public Book getBook(int book_id ){
		return bookDao.getBook(book_id);
	}
	//��ȡList<Book>����
	public Book getBook(LikeQueryBook likeQueryBook){
		return bookDao.getForListWithLikeQueryBook(likeQueryBook);
	}
/*	public Book getBook(LikeQueryBook likeQueryBook){
		return bookDao.getForListWithLikeQueryBook(likeQueryBook);
	}*/
}
