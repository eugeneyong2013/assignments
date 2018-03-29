package com.optimum.connection;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JndiConnector {
	
	@Resource(name="jee.eugene")
	private DataSource dataSource;
	private Connection connection;
	private static JndiConnector instance;
	
	private JndiConnector() {
		try {
			
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jee.eugene");
           
			// Attempting connection to database 
			this.connection = dataSource.getConnection();	
			System.out.println("Singleton: connected.");
		}
		catch(Exception e) {
			System.out.println("Database cannot be connected: " + e.getMessage());
		}
	} // end of constructor
	

	public Connection getConnection() {
		return connection;				
	} // end of getConnection
	
    public static JndiConnector getInstance() {
    	try {
            if (instance == null) {
                instance = new JndiConnector();
            } else if (instance.getConnection().isClosed()) {
                instance = new JndiConnector();
            }
    	}catch(Exception e) {
    		System.out.println("Cannot get instance.");
    	}
		return instance;
    } // end of getInstance
	
} // end of JndiConnector
