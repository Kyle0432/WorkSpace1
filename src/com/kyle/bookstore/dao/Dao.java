package com.kyle.bookstore.dao;

import java.util.List;

public interface Dao<T> {
	//���е�¼��֤����
	void getlogin(String sql,Object ... agrs);
	//������ɾ�Ĳ���
	void update(String sql,Object ... agrs);
	//���л�ȡһ���������
	T get(String sql,Object ... args);
	//���л�ȡ�ö����һ������
	List<T> getForList(String sql , Object ... args);
    //��ȡһ����¼�ĵ�ĳ���ֶλ��ѯĳ��ͳ����Ϣ
    <E> E getForValue(String sql, Object ... agrs);
    //�������²���
    void batch(String sql, Object[] ... params);
}
