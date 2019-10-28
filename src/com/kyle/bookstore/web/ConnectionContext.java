package com.kyle.bookstore.web;

import java.sql.Connection;

public class ConnectionContext {
	//相当于一个单例模式   
	//私有构造器
     private ConnectionContext(){}
    
     private static ConnectionContext instance = new ConnectionContext();
     
     public static ConnectionContext getInstance(){
              return instance;    	 
     }
     
     private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
     
     public void bind(Connection connection){
    	 connectionThreadLocal.set(connection);
     }
     public Connection get(){
    	 return connectionThreadLocal.get();
     }
     public void remove(){
    	 connectionThreadLocal.remove();
     }
     
}
