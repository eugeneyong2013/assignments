package com.optimum.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {

	public Connection getConnection() {
		
		try {
			
			// Variables needed for connection 
			String dbUrl = "jdbc:mysql://localhost:3306/info";
			String dbUsername = "root";
			String dbPassword = "admin";
			
			// Calls upon the methods in the class driver 
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = null;
			
			// Attempting connection to database 
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);		
			
			return conn;
			
		}
		catch(Exception e) {
			return null;
		}
					
	} // end of main 
	
} // end of SqlConnector
