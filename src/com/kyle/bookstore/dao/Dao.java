package com.kyle.bookstore.dao;

import java.util.List;

public interface Dao<T> {
	//进行登录验证操作
	void getlogin(String sql,Object ... agrs);
	//进行增删改操作
	void update(String sql,Object ... agrs);
	//进行获取一个对象操作
	T get(String sql,Object ... args);
	//进行获取该对象的一个集合
	List<T> getForList(String sql , Object ... args);
    //获取一条记录的的某个字段或查询某个统计信息
    <E> E getForValue(String sql, Object ... agrs);
    //批量更新操作
    void batch(String sql, Object[] ... params);
}
