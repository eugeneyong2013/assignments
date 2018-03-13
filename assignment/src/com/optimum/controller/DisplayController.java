package com.optimum.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.optimum.dao.DisplayDAO;
import com.optimum.pojo.Account;


public class DisplayController {
	
	DisplayDAO refDisplayDAO = new DisplayDAO();
	Scanner sc = new Scanner(System.in);
	String email;
	String password;
	String name;
	String nric;
	String dob;
	String mobile;
	boolean forgotPassword;
	int newMobile;
	int attempts = 0;
	
	public void showHomeScreen() {
		System.out.println("1. Login");
		System.out.println("2. Forgot Password");
		System.out.println();
		System.out.print("Enter a choice: ");
		int choice = sc.nextInt();
		
		if(choice == 1) {
			showLoginField();
		}
		if(choice == 2) {
			showForgotPasswordScreen();
		}
	} // end of showHomeScreen
	
	
	public void showForgotPasswordScreen() {
		forgotPassword = true;
		showLoginField();
	} // end of showForgotPasswordScreen
	
	
	public void showLoginField() {
		System.out.print("Login ID: ");
		email = sc.next();
		
		// To check and validate email pattern
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        boolean validateEmail = m.matches();
		
        // if choice is forgotPassword
        if(validateEmail == true && refDisplayDAO.checkLoginId(email) == true && forgotPassword == true) {
        	refDisplayDAO.validateSecurityQnAndAns(email);
        }
        else if(validateEmail == true && refDisplayDAO.checkLoginId(email) == true) {
			showPasswordField();
        }else {
			System.out.println("Invalid ID please try again.");
			// Reappears if login is wrong
			showLoginField();
		}	
		
	} // end of showLoginField
	
	public void showPasswordField() {
		
		String status = refDisplayDAO.getUserStatus(email);
		
		if(status.equals("lock")) {
			System.out.println("Account is locked.");
			System.out.print("Return back to home? (Y/N): ");
			String home = sc.next();
			
			if(home.equals("Y")) {
				showHomeScreen();
			}else if(home.equals("N")) {
				showLoginField();
			}else {
				System.out.println("Enter a valid option.");
				showPasswordField();
			}

		}
		
		System.out.print("Password: ");
		password = sc.next();
			
		if(refDisplayDAO.checkPassword(password).equals("trueAdmin")) {
			showAdminScreen();
		}else if(refDisplayDAO.checkPassword(password).equals("trueUser")) {
			showUserScreen();			
		}else {
			System.out.println("Invalid password, please try again.");
			attempts += 1;
			int attemptsLeft = 3 - attempts;
			
			if(attemptsLeft == 0) {
				System.out.println("Your account is locked.");
				System.out.println();
				refDisplayDAO.changeUserStatus(email);
			}
			
			System.out.println("You have " + attemptsLeft + " attempts left before account is locked.");		
			showPasswordField();
		}
		
	} // end of showPasswordField 
	
	public void showNameField() {
		System.out.print("Enter name: ");
		name = sc.next();
		
		// To check and validate the name pattern
	    boolean validateName = name.matches("[a-zA-Z]*");
	    
	    if(validateName == true) {
	    	showNricField();
	    }else {
	    	System.out.println("Please enter a valid name.");
	    	showNameField();
	    }
	} // end of showNameField
	
	public void showNricField() {
		System.out.print("Enter nric (Upper Case): ");
		nric = sc.next();
		boolean validateNric = nric.matches("\\p{Upper}\\d{7}\\p{Upper}");
		
		if(validateNric == true) {
			showEmailField();
		}else {
			System.out.println("Please enter a valid nric.");
			showNricField();
		}
	} // end of showNricField
	
	public void showEmailField() {
		System.out.print("Enter email: ");
		email = sc.next();
		
		// To check and validate email pattern
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        boolean validateEmail = m.matches();
		
		if(validateEmail == true) {
			showDobField();
		}else {
			System.out.println("Invalid email please try again.");
			// Reappears if login is wrong
			showEmailField();
		}	
		
	} // end of showLoginField
	
	public void showDobField() {
		System.out.print("Enter date of birth (dd/mm/yyyy): ");
		dob = sc.next();
		
		Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		Matcher match = pattern.matcher(dob);
		boolean validateDob = match.matches();
		
		if(validateDob == true) {
			showMobileField();
		}else {
			System.out.println("Please enter a valid date of birth.");
			showDobField();
		}
	} // end of showDobField
	
	public void showMobileField() {
		System.out.print("Enter mobile: ");
		mobile = sc.next();
		
		String mPattern = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{8})$";
		Pattern pattern = Pattern.compile(mPattern);
		Matcher match = pattern.matcher(mobile);
		boolean validateMobile = match.matches();
		
		if(validateMobile == true) {
			newMobile = Integer.parseInt(mobile);
			System.out.println();
			showAccount();
		}else {
			System.out.println("Please enter a valid mobile.");
			showMobileField();
		}
	} // end of showMobileField 
	
	public void showUserScreen() {
		
		System.out.println("\n Welcome user!");
		showLogout();
	} // end of showUserScreen 
	
	public void showLogout() {
		System.out.print("Logout? (Y/N): ");
		String logout = sc.next();
		
		if(logout.equals("Y")) {
			System.out.println();
			showHomeScreen();
		}else if(logout.equals("N")) {
			showLogout();
		}else {
			System.out.println("Please enter a valid option.");
			showLogout();
		}
	} // end of showLogout
	
	
	public void showAdminScreen() {
		System.out.println("\nWelcome admin!");
		System.out.println("1. Register new user");
		System.out.println("2. View user list");
		System.out.println("3. Logout");
		System.out.println();
		System.out.print("Enter choice: ");
		int adminChoice = sc.nextInt();
		
		if(adminChoice == 1) {
			showNameField();
		}
		if(adminChoice == 2) {
			refDisplayDAO.viewUserList();
		}
		if(adminChoice == 3) {
			showHomeScreen();
		}	
	} // end of showAdminScreen
	
	
	public void showAccount() {
			
		// to create a temporary password for first time users
		String password = nric.substring(1,5) + mobile.substring(4,8);

		Account refAccount = new Account(name,nric,email,dob,newMobile,password,"","","",1,"");
		
		// send it to DAO to update into sql database
		refDisplayDAO.registerNewUser(refAccount);
	}
	
} // end of DisplayController 
