package com.optimum.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {
	
	private Connection connection;
	private static SqlConnector instance;
	
	private SqlConnector() {
		try {
			
			// Variables needed for connection 
			String dbUrl = "jdbc:mysql://localhost:3306/info";
			String dbUsername = "root";
			String dbPassword = "admin";
			
			// Calls upon the methods in the class driver 
			Class.forName("com.mysql.jdbc.Driver");
			
			// Attempting connection to database 
			this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);		
		}
		catch(Exception e) {
			System.out.println("Database cannot be connected: " + e.getMessage());
		}
	} // end of constructor
	

	public Connection getConnection() {
		return connection;				
	} // end of getConnection
	
    public static SqlConnector getInstance() {
    	try {
            if (instance == null) {
                instance = new SqlConnector();
            } else if (instance.getConnection().isClosed()) {
                instance = new SqlConnector();
            }
    	}catch(Exception e) {
    		System.out.println("Cannot get instance.");
    	}
		return instance;
    }
	
} // end of SqlConnector
