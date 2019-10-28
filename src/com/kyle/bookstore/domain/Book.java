package com.kyle.bookstore.domain;

import java.sql.Date;

public class Book {
	private Integer book_id;
	private String author;
	
	private String title;
	private float price;
	
	private Date publishing_date;
	private int sales_amount;
	
	private int store_number;
	private String remark;
	private String images;
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getPublishing_date() {
		return publishing_date;
	}
	public void setPublishing_date(Date publishing_date) {
		this.publishing_date = publishing_date;
	}
	public int getSales_amount() {
		return sales_amount;
	}
	public void setSales_amount(int sales_amount) {
		this.sales_amount = sales_amount;
	}
	public int getStore_number() {
		return store_number;
	}
	public void setStore_number(int store_number) {
		this.store_number = store_number;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", author=" + author + ", title="
				+ title + ", price=" + price + ", publishing_date="
				+ publishing_date + ", sales_amount=" + sales_amount
				+ ", store_number=" + store_number + ", remark=" + remark
				+ ", images=" + images + "]";
	}
     
}
