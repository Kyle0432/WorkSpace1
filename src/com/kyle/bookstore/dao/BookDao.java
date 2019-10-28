package com.kyle.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.domain.ShoppingCartItem;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public interface BookDao {
    //根据id获取指定的Book对象
	public abstract Book getBook(int id);
	//根据CriteriaBook传入对象返回对应的Page
	public abstract Page<Book> getPage(CriteriaBook cb);
	//根据传入的CriteriaBook对象返回对应的 记录数
	public abstract long getTotalBookNumber(CriteriaBook cb);
	//根据传入的CriteriaBook对象和pageSize返回当前页的List
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);
	//根据传入的对象进行模糊查询
	public abstract Book getForListWithLikeQueryBook(LikeQueryBook lqb);
	//返回指定id的book的StoreNumber字段的值
	public abstract  int getStoreNumber(Integer id);
	//根据传入的ShoppingCartItem的集合,批量更新book数据表里storenumber和saleamount字段的值
	public abstract  void  batchupdateStoreNumberAndSaleAmount(Collection<ShoppingCartItem> item);
}
