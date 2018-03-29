package com.optimum.dao;

import java.util.ArrayList;

import com.optimum.pojo.Employee;

public interface EmployeeDAO {
	
	// to authenticate login credentials 
	public Employee loginAuthenticate(Employee refEmployee);
	
	// to authenticate if the login email is correct, then send the password to the email
	public boolean forgotPasswordAuthenticate(Employee refEmployee);
	
	// to register a new user and to insert it into the sql database
	public String registerNewEmployee(Employee refEmployee);
	
	// to get all users from the sql database and print it in table form in jsp 
	public ArrayList<Employee> getEmployeeList();
	
	// to get all unlocked users from the sql database and print it in table form in jsp 
	public ArrayList<Employee> getUnlockedEmployeeList();
	
	// to get all locked users from the sql database and print it in table form in jsp 
	public ArrayList<Employee> getLockedEmployeeList();
	
	// to send forgotten password to the user 
	public void sendMail(String regName, String regEmail, String regPassword);
	
	// to change the status of the user
	public void updateStatus(String refEmployeeEmail);
	
	// to update last login time and date 
	public void updateLastLogin(Employee refEmployee);
	
	// to update user's credentials
	public String updateEmployee(Employee refEmployee);
	
	// to get image
	public byte[] getImage(String emailAddress);
	
	// to hash the password
	public String hashPassword(String password);
	
} // end of UserDAO
