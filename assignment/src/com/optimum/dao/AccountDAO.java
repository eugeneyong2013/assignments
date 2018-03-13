package com.optimum.dao;

import com.optimum.pojo.Account;

public interface AccountDAO {
	public boolean checkLoginId(String refEmail);
	public boolean enterTempPassword();
	public void enterNewPasswordAndRetype();
	public void choosingSecurityQns();
	public boolean validateSecurityQnAndAns(String refEmail);
	public String checkPassword(String refPassword);
	public void registerNewUser(Account account);
	public void sendMail(String regName, String regEmail, String regPassword);
	public void viewUserList();
	public void changeUserStatus(String refUserToChangeStatus);
	public String getUserStatus(String statusUser);
} // end of AccountDAO 
