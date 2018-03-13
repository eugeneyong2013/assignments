package com.optimum.pojo;

import java.util.Date;

public class Account {
	
	//global variables 
	private String name;
	private String nric;
	private String email;
	private String dob;
	private int mobile;
	private String password;
	private String role;
	private String securityQn;
	private String securityAns;
	private int firstLogin;
	private String status;
	
	public Account(){
		
	} // end of constructor
	
	public Account(String name, String nric, String email, String dob, int mobile, String password, String role, String securityQn, String securityAns, int firstLogin, String status){
		this.name = name;
		this.nric = nric;
		this.email = email;
		this.dob = dob;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
		this.securityAns = securityAns;
		this.securityQn = securityQn;
		this.firstLogin = firstLogin;
		this.status = status;
	} // end of Constructor 

	public String getName() {
		return name;
	} // end of getName

	public void setName(String name) {
		this.name = name;
	} // end of setName 

	public String getNric() {
		return nric;
	} // end of getNric 

	public void setNric(String nric) {
		this.nric = nric;
	} // end of setNric

	public String getEmail() {
		return email;
	} // end of getEmail

	public void setEmail(String email) {
		this.email = email;
	} // end of setEmail 

	public String getDob() {
		return dob;
	} // end of getDob

	public void setDob(String dob) {
		this.dob = dob;
	} // end of setDob

	public int getMobile() {
		return mobile;
	} // end of getMobile

	public void setMobile(int mobile) {
		this.mobile = mobile;
	} // end of setMobile

	public String getPassword() {
		return password;
	} // end of getPassword 

	public void setPassword(String password) {
		this.password = password;
	} // end of setPassword

	public String getRole() {
		return role;
	} // end of getRole 

	public void setRole(String role) {
		this.role = role;
	} // end of setRole 

	public String getSecurityQn() {
		return securityQn;
	} // end of getSecurityQn

	public void setSecurityQn(String securityQn) {
		this.securityQn = securityQn;
	} // end of setSecurityQn 

	public String getSecurityAns() {
		return securityAns;
	} // end of getSecurityAns 

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	} // end of setSecurityAns 

	public int getFirstLogin() {
		return firstLogin;
	} // end of getFirstLogin

	public void setFirstLogin(int firstLogin) {
		this.firstLogin = firstLogin;
	} // end of setFirstLogin

	public String getStatus() {
		return status;
	} // end of getStatus 

	public void setStatus(String status) {
		this.status = status;
	} // end of setStatus
	
} // end of Account 
