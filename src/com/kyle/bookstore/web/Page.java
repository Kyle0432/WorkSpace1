package com.kyle.bookstore.web;

import java.util.List;

public class Page<T> {
     //当前第几页
	private int pageNo;
	 //当前页的list
	private List<T> list; 
	 //每页显示多少条记录
	private int pageSize = 6;
	 //总共的记录数
	private long totalItemNumber;
	//①构造器中需要对 pageNo 进行初始化(因为要在数据库里传过来的)
	public Page(int pageNo){
		this.pageNo = pageNo;
	}
	//进行校验
	public int getPageNo(){
		if(pageNo < 0){
			pageNo = 1;
		}
		if(pageNo > getTotalPageNumber()){
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}
	
	public int getPageSize(){
		
		return pageSize;
	}
	//②进行初始化操作,从数据库获取list集合
	public void setList(List<T> list){
		this.list = list;
	}
	
	public List<T> getList(){
		return list;
	}
	
	public int getTotalPageNumber(){
		/*因为原本totalItemNumber在上面定义是long型的，所以
        在这里要转成int类型的*/
		int totalPageNumber = (int) (totalItemNumber / pageSize);
		
		if(totalItemNumber % pageSize != 0){
			totalPageNumber++;
		}
		return totalPageNumber;
	}
	//③进行初始化操作,从数据库里传送totalItemNumber
	public void setTotalItemNumber(long totalItemNumber){
		this.totalItemNumber = totalItemNumber;
	}
	//是否有下一页
	public boolean isHasNext(){
		if(getPageNo()<getTotalPageNumber()){
			return true;
		}
		    return false;
	}
	//是否有上一页
	public boolean isHasPrev(){
		if(getPageNo() > 1){
			return true;
		}
		return false;
	}
	//获取下一页的页码
	public int getNextPage(){
		if(isHasNext()){
			return getPageNo()+1;
		}
		return getPageNo();
	}
	//获取上一页的页码
	public int getPrevPage(){
		if(isHasPrev()){
			return getPageNo()-1;
		}
		return getPageNo();
	}
}
