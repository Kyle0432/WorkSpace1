package com.kyle.bookstore.web;

import java.util.List;

public class Page<T> {
     //��ǰ�ڼ�ҳ
	private int pageNo;
	 //��ǰҳ��list
	private List<T> list; 
	 //ÿҳ��ʾ��������¼
	private int pageSize = 6;
	 //�ܹ��ļ�¼��
	private long totalItemNumber;
	//�ٹ���������Ҫ�� pageNo ���г�ʼ��(��ΪҪ�����ݿ��ﴫ������)
	public Page(int pageNo){
		this.pageNo = pageNo;
	}
	//����У��
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
	//�ڽ��г�ʼ������,�����ݿ��ȡlist����
	public void setList(List<T> list){
		this.list = list;
	}
	
	public List<T> getList(){
		return list;
	}
	
	public int getTotalPageNumber(){
		/*��Ϊԭ��totalItemNumber�����涨����long�͵ģ�����
        ������Ҫת��int���͵�*/
		int totalPageNumber = (int) (totalItemNumber / pageSize);
		
		if(totalItemNumber % pageSize != 0){
			totalPageNumber++;
		}
		return totalPageNumber;
	}
	//�۽��г�ʼ������,�����ݿ��ﴫ��totalItemNumber
	public void setTotalItemNumber(long totalItemNumber){
		this.totalItemNumber = totalItemNumber;
	}
	//�Ƿ�����һҳ
	public boolean isHasNext(){
		if(getPageNo()<getTotalPageNumber()){
			return true;
		}
		    return false;
	}
	//�Ƿ�����һҳ
	public boolean isHasPrev(){
		if(getPageNo() > 1){
			return true;
		}
		return false;
	}
	//��ȡ��һҳ��ҳ��
	public int getNextPage(){
		if(isHasNext()){
			return getPageNo()+1;
		}
		return getPageNo();
	}
	//��ȡ��һҳ��ҳ��
	public int getPrevPage(){
		if(isHasPrev()){
			return getPageNo()-1;
		}
		return getPageNo();
	}
}
