package com.ems;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		try {
			 String url = DBconfig.getDbUrl();
	            String user = DBconfig.getDbUsername();
	            String password = DBconfig.getDbPassword();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
