package com.kyle.bookstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;


public class User {
	private int user_id;
	private String user_name;
	private String password;
	private int account_id;
	private Set<Trade> trades = new LinkedHashSet<Trade>();

	public User() {
		super();
	}

	public User(int user_id, String user_name, String password, int account_id) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.account_id = account_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", password=" + password + ", account_id=" + account_id + "]";
	}
}
