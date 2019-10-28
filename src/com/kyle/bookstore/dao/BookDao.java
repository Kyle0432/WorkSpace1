package com.kyle.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.kyle.bookstore.domain.Book;
import com.kyle.bookstore.domain.ShoppingCartItem;
import com.kyle.bookstore.web.CriteriaBook;
import com.kyle.bookstore.web.LikeQueryBook;
import com.kyle.bookstore.web.Page;

public interface BookDao {
    //����id��ȡָ����Book����
	public abstract Book getBook(int id);
	//����CriteriaBook������󷵻ض�Ӧ��Page
	public abstract Page<Book> getPage(CriteriaBook cb);
	//���ݴ����CriteriaBook���󷵻ض�Ӧ�� ��¼��
	public abstract long getTotalBookNumber(CriteriaBook cb);
	//���ݴ����CriteriaBook�����pageSize���ص�ǰҳ��List
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);
	//���ݴ���Ķ������ģ����ѯ
	public abstract Book getForListWithLikeQueryBook(LikeQueryBook lqb);
	//����ָ��id��book��StoreNumber�ֶε�ֵ
	public abstract  int getStoreNumber(Integer id);
	//���ݴ����ShoppingCartItem�ļ���,��������book���ݱ���storenumber��saleamount�ֶε�ֵ
	public abstract  void  batchupdateStoreNumberAndSaleAmount(Collection<ShoppingCartItem> item);
}
