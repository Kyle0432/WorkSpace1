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
	
	//��ȡ����Page<Book��Ϣ����
	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		//ʵ��������
		Page<Book> page = new Page<Book>(cb.getPageNo());
		//����ȫ���ļ�¼
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//У��PageNo�ĺϷ���
		cb.setPageNo(page.getPageNo());
		//��list����Ϊ����book.jsp�������ѭ��
		page.setList(getPageList(cb, 6));
		
		return page;
	}
    //��ʾ�ڼ۸�����ķ�Χ���ж�������¼
	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT count(book_id) FROM book WHERE price >= ? AND price <= ?";
		return getForValue(sql, cb.getMinPrice(),cb.getMaxPrice());
	}
    //��ʾ�ڼ۸�����ķ�Χ�ڶ�����¼��list����
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT book_id, author, title, price, publishing_date, " +
				"sales_amount, store_number, remark ,images FROM book " +
				"WHERE price >= ? AND price <= ? " +
				"LIMIT ?, ?";
		return getForList(sql, cb.getMinPrice(), cb.getMaxPrice(), 
				//��ʾ��ȡ��ǰҳ�ĵ�һ����¼��ҳ��
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
