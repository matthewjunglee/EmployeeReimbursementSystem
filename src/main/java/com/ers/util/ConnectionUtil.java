package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private final String url = "jdbc:postgresql://revature.cd0uyuacqqon.us-west-1.rds.amazonaws.com:5432/postgres?currentSchema=ers";
	private final String username = "ers_user";
	private final String password = "project1";
	
	private static ConnectionUtil instance;
	
	private ConnectionUtil() {}
	
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionUtil getInstance() {
		if (instance == null) {
			instance = new ConnectionUtil();
		}
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
}
