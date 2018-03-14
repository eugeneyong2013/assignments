package com.optimum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.optimum.connection.SqlConnector;
import com.optimum.controller.DisplayController;
import com.optimum.pojo.Account;

public class AccountDAOImpl implements AccountDAO {
	
	// Establishing a connection to the database
	Connection conn = SqlConnector.getInstance().getConnection();
	DisplayController refDisplayController = new DisplayController();
	Scanner sc = new Scanner(System.in);
	String email;
	String password;
	String name;
	String role;
	String status;
	int firstLogin;
	String securityQn;
	String securityAns;
	String userToChangeStatus;

	@Override
	public boolean checkLoginId(String refEmail) {
			
			try {
				
				Statement statement = conn.createStatement();
				// To store the results from the search
				ResultSet result = statement.executeQuery("select name,email,firstLogin,password from credentials where email = \'" + refEmail + "\'");
				while(result.next()) {
					name = result.getString("name");
					email = result.getString("email");
					firstLogin = result.getInt("firstLogin");
				}
				
				if(email.equals(refEmail) && firstLogin == 1) {
					enterTempPassword();
				}
			
				// To check if the user input is the same as the stored input 
				if(email.equals(refEmail)) {
					return true;
				}
			
			}catch(Exception e) {
				return false;
			}
				
		return false;
	} // end of checkLoginId 
	
	@Override
	public boolean enterTempPassword() {
		
		System.out.print("Temp password: ");
		String tempPass = sc.next();
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select password from credentials where email = \'" + email + "\'");
			
			while(result.next()) {
				password = result.getString("password");
			}
			
			if(tempPass.equals(password)) {
					enterNewPasswordAndRetype();
			}else {
				enterTempPassword();
			}
			
		}catch(Exception e) {
			System.out.println("Error in getting password.");
		}
	
		return false;
	} // end of enterTempPassword
	
	@Override
	public void enterNewPasswordAndRetype() {
		
		System.out.print("New password: ");
		String newPassword = sc.next();
		
		System.out.print("Retype new password: ");
		String retypeNewPassword = sc.next();
		
		// new password and retyped password needs to be the same
		if(!newPassword.equals(retypeNewPassword)) {
			System.out.println("Please enter the same password.");
			enterNewPasswordAndRetype();
		}
		
		try {
			// create query to be passed on to update database in sql 
			String query = "update credentials set password = \'" + retypeNewPassword + "\', firstLogin = 2 where email = \'" + email + "\'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
			choosingSecurityQns();
			refDisplayController.showUserScreen();
		}catch(Exception e) {
			System.out.println("Password cannot be changed.");
		}
	} // end of enterNewPassword
	
	@Override
	public void choosingSecurityQns() {
		
		// setting the qns of choice
		String qn1 = "favorite book";
		String qn2 = "favorite color";
		String qn3 = "favorite sport";
		
		System.out.println("Security Questions:");
		System.out.println("\t a. " + qn1);
		System.out.println("\t b. " + qn2);
		System.out.println("\t c. " + qn3);
		System.out.print("Enter question of choice: ");
		String qnChoice = sc.next();
		
		// if the option isn't of choice reprompt again
		if(!qnChoice.equals("a") && !qnChoice.equals("b") && !qnChoice.equals("c")) {
			System.out.println("Please enter a valid option.");
			choosingSecurityQns();
		}
		
		// putting the qn into the choice 
		if(qnChoice.equals("a")) {
			qnChoice = qn1;
		}else if(qnChoice.equals("b")) {
			qnChoice = qn2;
		}else if(qnChoice.equals("c")) {
			qnChoice = qn3;
		}
		
		
		System.out.print("Enter answer to question (One word): ");
		String ansChoice = sc.next();
		
		if(qnChoice != null && ansChoice != null) {
			try {
				String query = "update credentials set securityQn = \'" + qnChoice + "\', securityAns = \'" + ansChoice + "\' where email = \'" + email + "\'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.executeUpdate();
				DisplayController refDisplayController = new DisplayController();
				refDisplayController.showUserScreen();
				
			}catch(Exception e) {
				System.out.println("Security qns was not updated.");
			}
		}
		
	} // end of choosingSecurityQns 
	
	@Override
	public boolean validateSecurityQnAndAns(String email) {
		
		
		try {
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select securityQn,securityAns from credentials where email = \'" + email + "\'");
			
			// to go through and get result
			while(result.next()) {
				securityQn = result.getString("securityQn");
				securityAns = result.getString("securityAns");
			}
			
			System.out.println("Security Question: " + securityQn);
			System.out.print("Enter answer: ");
			String answer = sc.next();
					
			if(answer.equals(securityAns)) {
				enterNewPasswordAndRetype();
			}else {
				System.out.println("Please enter a valid answer.");
				validateSecurityQnAndAns(email);
			}
			
		}catch(Exception e) {
			System.out.println("Cannot validate.");
		}
								
		return false;
	} // end of validateSecurityQnAndAns
	
	@Override
	public String checkPassword(String refPassword) {
			
		try {
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select password,role from credentials where password = \'" + refPassword + "\'");
			
			// to go through and get result
			while(result.next()) {
				password = result.getString("password");
				role = result.getString("role");
			}
		
			// To check if the user input is the same as the stored input 			
			if(password.equals(refPassword) && role.equals("user")) {
				return "trueUser";
			}
			
			if(password.equals(refPassword) && role.equals("admin")) {
				return "trueAdmin";
			}
		
		}catch(Exception e) {
			return "false";
		}
			
		return "false";
		
	} // end of checkPassword
	
	@Override
	public void registerNewUser(Account account) {
		
		Boolean noDuplicate = true;
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("Select nric,email,mobile from credentials");
			
			String nricResult, emailResult;
			int mobileResult;
			
			while(result.next()) {
				nricResult = result.getString("nric");
				emailResult = result.getString("email");
				mobileResult = result.getInt("mobile");
				
				if(nricResult.equals(account.getNric())) {
					System.out.println("The NRIC have been used before. Please try again.");
					refDisplayController.showNameField();
				}else if(emailResult.equals(account.getEmail())) {
					System.out.println("The Email have been used before. Please try again.");
					refDisplayController.showNameField();
				}else if(mobileResult == account.getMobile()) {
					System.out.println("The Mobile have been used before. Please try again.");
					refDisplayController.showNameField();
				}else {
					noDuplicate = true;
				}
				
			}
			
			if(noDuplicate == true) {
				String year = account.getDob().substring(6);
				String month = account.getDob().substring(3, 5);
				String day = account.getDob().substring(0, 2);
				String dob = year + "-" + month + "-" + day;
				
				// create query to be passed on to update database in sql 
				String query = "insert into credentials(name,nric,email,dob,mobile,password) values(?,?,?,?,?,?)";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, account.getName());
				preparedStatement.setString(2, account.getNric());
				preparedStatement.setString(3, account.getEmail());
				preparedStatement.setDate(4, Date.valueOf(dob));
				preparedStatement.setInt(5, account.getMobile());
				preparedStatement.setString(6, account.getPassword());
				preparedStatement.executeUpdate();
			}
						
		}catch(Exception e) {
			System.out.println("Account cannot be created.");
			System.out.println(e.getMessage());
		}

		sendMail(account.getName(),account.getEmail(),account.getPassword());
	} // end of registerNewUser
	
	@Override
	public void sendMail(String regName, String regEmail, String regPassword) {
				
		  String to = regEmail; //change accordingly  
	      String from = "eugene.yong.2013@gmail.com"; 
	      String passwordEmail = "armormew2";
	      
		  //Get the session object  
	      Properties props = System.getProperties();  
		  props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		  props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		  props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		  props.put("mail.smtp.port", "465"); // SMTP Port
	      
		  Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, passwordEmail);
				}
			};
			
		 Session session = Session.getDefaultInstance(props, auth); 
	  
	     //compose the message  
	      try{  
	        	  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject("Temp message");  
	         message.setText("Hello " + regName + "! This is the admin, the following is your temporary password: " + regPassword);  
	     
	         // Send message  
	         Transport.send(message);  
	         System.out.println("Temporary password has been send to " + regEmail + "!");
	         refDisplayController.showAdminScreen();
	  
	      }catch (Exception mex) {
	    	  mex.printStackTrace();
	      }  
	     
	} // end of sendMail 
	
	@Override
	public void viewUserList() {
		try {
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select name,email,status from credentials where role = \'user\'");
			
			// to go through and get result
			System.out.println("Name \tEmail \t                Status");
			System.out.println("-------------------------------------");
			while(result.next()) {
				name = result.getString("name");
				email = result.getString("email");
				status = result.getString("status");
				System.out.println(name + "\t" + email + "\t" + status);
			}
			
			System.out.println();
			System.out.println("1. Change user status");
			System.out.println("2. Back");
			System.out.println();
			System.out.print("Enter Choice: ");
			int choice = sc.nextInt();
			
			// to change user status between lock and unlock 
			if(choice == 1) {
				System.out.print("Enter user's login ID (email): ");
				String userToChange = sc.next();
				
				result = statement.executeQuery("select status from credentials where email = \'" + userToChange + "\'");
				
				userToChangeStatus = null;
				
				// to get status of the user chosen so we can see which option to change it to
				while(result.next()) {
					userToChangeStatus = result.getString("status");
				}
				
				if(userToChangeStatus.equals("unlock")) {
					String query = "update credentials set status = \'lock\' where email = \'" + userToChange + "\'";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					preparedStatement.executeUpdate();
					viewUserList();
					
				}else if(userToChangeStatus.equals("lock")) {
					String query = "update credentials set status = \'unlock\' where email = \'" + userToChange + "\'";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					preparedStatement.executeUpdate();
					viewUserList();
				}
			}
			// back to admin homepage
			if(choice == 2) {
				refDisplayController.showAdminScreen();
			}
			
			
		}catch(Exception e) {
			System.out.println("User list cannot be retrieved.");
		}
	} // end of viewUserList
	
	@Override
	public void changeUserStatus(String refUserToChangeStatus) {
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select status from credentials where email = \'" + refUserToChangeStatus + "\'");
			
			while(result.next()) {
				status = result.getString("status");
			}
			
			if(status.equals("unlock")) {
				String query = "update credentials set status = \'lock\' where email = \'" + refUserToChangeStatus + "\'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.executeUpdate();	
				refDisplayController.showHomeScreen();
				
			}else if(status.equals("lock")) {
				String query = "update credentials set status = \'unlock\' where email = \'" + refUserToChangeStatus + "\'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.executeUpdate();
				refDisplayController.showHomeScreen();
			}
		}catch(Exception e) {
			System.out.println("Cannot change status.");
		}

	} // end of changeUserStatus
	
	@Override
	public String getUserStatus(String statusUser) {
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select status from credentials where email = \'" + statusUser + "\'");
			
			while(result.next()) {
				status = result.getString("status");
			}
			
			return status;
			
		}catch(Exception e) {
			return "Cannot get status.";
		}
	} // end of getUserStatus
	
} // end of displayDAO 
