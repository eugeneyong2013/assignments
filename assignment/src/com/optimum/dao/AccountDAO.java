package com.optimum.dao;

import com.optimum.pojo.Account;

public interface AccountDAO {
	
	// inputs email to check if the login ID is in the database or not
	public boolean checkLoginId(String refEmail);
	
	// inputs temp password and is verified via the database 
	public boolean enterTempPassword();
	
	// compares new password and retype one to make sure it is the same and then uploads it to the database 
	public void enterNewPasswordAndRetype();
	
	// shows the security qns where user can choose one and together with the answer, it will be uploaded to the database 
	public void choosingSecurityQns();
	
	// when user chooses the qn and ans, retrieve it from the database and compare if it is correct 
	public boolean validateSecurityQnAndAns(String refEmail);
	
	// to compare the input password with the database one to see if it is correct  
	public String checkPassword(String refPassword);
	
	// to create a new account and upload to the database 
	public void registerNewUser(Account account);
	
	// to send the mail through gmail  
	public void sendMail(String regName, String regEmail, String regPassword);
	
	// to populate a list of lists where admin can see 
	public void viewUserList();
	
	// to change the status of a user from lock to unlock and vice versa 
	public void changeUserStatus(String refUserToChangeStatus);
	
	// to get the user's status  
	public String getUserStatus(String statusUser);
} // end of AccountDAO 
