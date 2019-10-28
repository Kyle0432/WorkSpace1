package com.kyle.bookstore.dao.impl;

import java.util.Collection;
import java.util.List;

import com.kyle.bookstore.dao.BookDao;
import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.domain.ShoppingCartItem;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public class BookDaoImpl extends BaseDao<Book>implements BookDao {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT book_id,author,title,price,publishing_date,"
				+ "sales_amount,store_number,remark,images FROM book WHERE book_id = ?";
		return get(sql, id);
	}
	
	@Override
	public Book getForListWithLikeQueryBook(LikeQueryBook lqb) {
        String sql ="SELECT book_id,author,title,price,publishing_date,sales_amount,store_number,remark,images FROM book WHERE remark LIKE ?";
		return get(sql, lqb.getRemark());
	}
     
/*	@Override
	public Book getForListWithLikeQueryBook(LikeQueryBook lqb) {
        String sql ="SELECT * FROM book WHERE remark LIKE ?";
		return get(sql, lqb.getRemark());
	}*/
	
	//获取整个Page<Book信息集合
	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		//实例化对象
		Page<Book> page = new Page<Book>(cb.getPageNo());
		//保存全部的记录
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//校验PageNo的合法性
		cb.setPageNo(page.getPageNo());
		//此list集合为了在book.jsp里面遍历循环
		page.setList(getPageList(cb, 6));
		
		return page;
	}
    //表示在价格区间的范围内有多少条记录
	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT count(book_id) FROM book WHERE price >= ? AND price <= ?";
		return getForValue(sql, cb.getMinPrice(),cb.getMaxPrice());
	}
    //表示在价格区间的范围内多条记录的list集合
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT book_id, author, title, price, publishing_date, " +
				"sales_amount, store_number, remark ,images FROM book " +
				"WHERE price >= ? AND price <= ? " +
				"LIMIT ?, ?";
		return getForList(sql, cb.getMinPrice(), cb.getMaxPrice(), 
				//表示获取当前页的第一条记录的页码
				(cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		return 0;
	}

	@Override
	public void batchupdateStoreNumberAndSaleAmount(
			Collection<ShoppingCartItem> item) {
		
	}


}
