package com.kyle.bookstore.web;

public class LikeQueryBook {
	
	private String remark;
/*	private String author;
	private String images;
	private float price;
	private String remark;*/
	
	
	public LikeQueryBook(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		if(remark == null)
			remark="%%";
		else
			remark = "%"+ remark +"%";
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}*/

 
}
