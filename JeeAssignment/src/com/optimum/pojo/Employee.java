package com.optimum.pojo;

import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.Part;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;

public class Employee {
	
	// global variables
	private int employeeId;
	private String password;
	private String fullName;
	private String nric;
	private String gender;
	private String dob;
	private String address;
	private String country;
	private String qualification;
	private String department;
	private String emailAddress;
	private String mobile;
	private String status;
	private String role;
	private String hashPassword;
	private Date lastLogin;
	private Part certificate;
	private Part profilePic;
	private InputStream profilePicStream;
	private InputStream certificateStream;
	private EmployeeDAO refEmployeeDAO = new EmployeeDAOImpl();
	
	public int getEmployeeId() {
		return employeeId;
	} // end of getEmployeeID
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	} // end of setEmployeeID
	
	public String getPassword() {
		return password;
	} // end of getPassword
	
	public void setPassword(String password) {
		this.password = refEmployeeDAO.hashPassword(password);
	} // end of setPassword 
	
	public String getFullName() {
		return fullName;
	} // end of getFullName 
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	} // end of setFullName
	
	public String getNric() {
		return nric;
	} // end of getNric 
	
	public void setNric(String nric) {
		this.nric = nric;
	} // end of setNric 
	
	public String getGender() {
		return gender;
	} // end of getGender
	
	public void setGender(String gender) {
		this.gender = gender;
	} // end of setGender
	
	public String getDob() {
		return dob;
	} // end of getDob
	
	public void setDob(String dob) {
		this.dob = dob;
	} // end of setDob
	
	public String getAddress() {
		return address;
	} // end of getAddress
	
	public void setAddress(String address) {
		this.address = address;
	} // end of setAddress
	
	public String getCountry() {
		return country;
	} // end of getCountry
	
	public void setCountry(String country) {
		this.country = country;
	} // end of setCountry 
	
	public String getQualification() {
		return qualification;
	} // end of getQualification
	
	public void setQualification(String qualification) {
		this.qualification = qualification;
	} // end of setQualification
	
	public String getDepartment() {
		return department;
	} // end of getDepartment
	
	public void setDepartment(String department) {
		this.department = department;
	} // end of setDepartment
	
	public String getEmailAddress() {
		return emailAddress;
	} // end of getEmailAddress
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	} // end of setEmailAddress
	
	public String getMobile() {
		return mobile;
	} // end of getMobile
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	} // end of setMobile
	
	public String getStatus() {
		return status;
	} // end of getStatus
	
	public void setStatus(String status) {
		this.status = status;
	} // end of setStatus
	
	public String getRole() {
		return role;
	} // end of getRole
	
	public void setRole(String role) {
		this.role = role;
	} // end of setRole
	
	public Date getLastLogin() {
		return lastLogin;
	} // end of getLastLogin
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	} // end of setLastLogin
	
	public Part getCertificate() {
		return certificate;
	} // end of getCertificate
	
	public void setCertificate(Part certificate) {
		this.certificate = certificate;
	} // end of setCertificate
	
	public Part getProfilePic() {
		return profilePic;
	} // end of getProfilePic
	
	public void setProfilePic(Part profilePic) {
		this.profilePic = profilePic;
	} // end of setProfilePic
	
	public InputStream getProfilePicStream() {
		return profilePicStream;
	} // end of getProfilePicStream
	
	public void setProfilePicStream(InputStream profilePicStream) {
		this.profilePicStream = profilePicStream;
	} // end of setProfilePiceStream

	public String getHashPassword() {
		return hashPassword;
	} // end of getHashPassword

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	} // end of setHashPassword

	public InputStream getCertificateStream() {
		return certificateStream;
	} // end of getCertificateStream

	public void setCertificateStream(InputStream certificateStream) {
		this.certificateStream = certificateStream;
	} // end of setCertificateStream
	
} // end of Employee
