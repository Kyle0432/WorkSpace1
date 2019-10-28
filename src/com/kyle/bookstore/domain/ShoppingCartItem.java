package com.kyle.bookstore.domain;


public class ShoppingCartItem {

   private int user_id;
   private Integer book_id;
   private int quantity;
   private String title;
   private double price;
   
public ShoppingCartItem(int id) {
     this.book_id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public Integer getBook_id() {
	return book_id;
}
public void setBook_id(Integer book_id) {
	this.book_id = book_id;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
   
  //获得总价 
public double getTotalPrice(){
	return price*quantity;
}
}
