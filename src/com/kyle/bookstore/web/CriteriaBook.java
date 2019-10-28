package com.kyle.bookstore.web;

public class CriteriaBook {
	//��������ݶ��Ǵ�DAO�ﴫ�͹�����,�����ݿ⽻��.
	private int pageNo;
	
	private float minPrice;
	
	private float maxPrice = Integer.MAX_VALUE;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public CriteriaBook(int pageNo, float minPrice, float maxPrice) {
		super();
		this.pageNo = pageNo;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	
}
