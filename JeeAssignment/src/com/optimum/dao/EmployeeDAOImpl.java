package com.optimum.dao;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import com.optimum.connection.JndiConnector;
import com.optimum.pojo.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	// global variables
	private Connection conn = JndiConnector.getInstance().getConnection();
	private boolean forgotPasswordStatus;
	static Logger log4j = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public Employee loginAuthenticate(Employee refEmployee) {
		
		Employee employee = new Employee();
		
		// to store information
		String emailAddress = "";
		String password = "";
		String role = "";
		String fullName = "";
		String country = "";
		String dob = "";
		String nric = "";
		String gender = "";
		String qualification = "";
		String department = "";
		String mobile = "";
		String status = "";
		String address = "";
		InputStream profilePicStream = null;
		InputStream certificateStream = null;
		int employeeId = 0;
		
		try {
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select * from jeecredentials where emailAddress = \'" + refEmployee.getEmailAddress() + "\'");
			
			// loop through to get information from sql database 
			while(result.next()) {
				emailAddress = result.getString("emailAddress");
				password = result.getString("password");
				role = result.getString("role");
				fullName = result.getString("fullName");
				country = result.getString("country");
				dob = result.getString("dob");
				nric = result.getString("nric");
				gender = result.getString("gender");
				qualification = result.getString("qualification");
				department = result.getString("department");
				mobile = result.getString("mobile");
				status = result.getString("status");
				employeeId = result.getInt("employeeId");
				address = result.getString("address");
				profilePicStream = result.getBinaryStream("profilePic");
				certificateStream = result.getBinaryStream("certificate");
			}
			
			// check if emailAddress and password is the same as the one from the sql database 
			if(emailAddress.equals(refEmployee.getEmailAddress()) && password.equals(refEmployee.getPassword())) {
				employee.setEmailAddress(emailAddress);
				employee.setHashPassword(password);
				employee.setRole(role);
				employee.setFullName(fullName);
				employee.setCountry(country);
				employee.setDob(dob);
				employee.setNric(nric);
				employee.setGender(gender);
				employee.setQualification(qualification);
				employee.setDepartment(department);
				employee.setMobile(mobile);
				employee.setStatus(status);
				employee.setAddress(address);
				employee.setEmployeeId(employeeId);
				employee.setProfilePicStream(profilePicStream);
				employee.setCertificateStream(certificateStream);
				log4j.debug("loginAuthenticate: can pull employee data out.");
				log4j.info(employee.getEmailAddress() + " has logged in.");
			}else {
				employee = null;
				log4j.warn("Employee credentials is null.");
			}
			
		}catch(Exception e) {
			System.out.println("Unable to execute loginAuthenticate");
			log4j.error("Unable to execute loginAuthenticate.");
			e.getMessage();
		}
	
		return employee;
		
	} // end of loginAuthenticate
	
	@Override 
	public boolean forgotPasswordAuthenticate(Employee refEmployee) {
		
		// to store information
		String emailAddress = "";
		String password = "";
		String fullName = "";
		String status = "";
		String nric = "";
		String mobile = "";
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select fullName,emailAddress,nric,mobile,status from jeecredentials where emailAddress = \'" + refEmployee.getEmailAddress() + "\'");
			
			// loop through to get information from sql database 
			while(result.next()) {
				emailAddress = result.getString("emailAddress");
				nric = result.getString("nric");
				fullName = result.getString("fullName");
				mobile = result.getString("mobile");
				status = result.getString("status");
			}
			
			// check if emailAddress is the same as the one from the sql database 
			if(emailAddress.equals(refEmployee.getEmailAddress())) {
				if(status.equals("unlock")) {
					forgotPasswordStatus = true;
					log4j.debug("forgotPasswordAuthenticate: email address is correct proceed to sending email");
					password = nric.substring(1, 5) + mobile.substring(4);
					sendMail(fullName,emailAddress,password);
					String passwordHash = hashPassword(password);
					String query = "update jeecredentials set password = \'"+ passwordHash + "\' where emailAddress = \'" + emailAddress + "\'";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					preparedStatement.executeUpdate();	
				}
			}else {
				forgotPasswordStatus = false;
			}
			
		}catch(Exception e) {
			System.out.println("Unable to execute forgotPasswordAuthenticate");
			e.getMessage();
		}
		
		return forgotPasswordStatus;
		
	} // end of forgotPasswordAuthenticate

	@Override
	public void sendMail(String regName, String regEmail, String regPassword) {
				
		  String to = regEmail; //change accordingly  
	      String from = "optimum.batch5@gmail.com"; 
	      String passwordEmail = "Optimum2018";
	      
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
	         message.setText("Hello " + regName + "! This is the admin, the following is your password: " + regPassword);  
	     
	         // Send message  
	         Transport.send(message);  
	         log4j.debug("sendEmail: email has been sent!");
	         System.out.println("Temporary password has been send to " + regEmail + "!");
	  
	      }catch (Exception mex) {
	    	  log4j.error("Unable to send the email to employee.");
	    	  mex.printStackTrace();
	      }  
	     
	} // end of sendMail 
	
	@Override
	public String registerNewEmployee(Employee refEmployee) {
		System.out.println("This is the name: " + refEmployee.getFullName());
		// to check if there is duplicate entry in the database
		Boolean noDuplicate = true;
		String message = "An error has occured. Please try again.";
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select nric,emailAddress,mobile from jeecredentials");
			
			String nricResult, emailResult, mobileResult;
			
			while(result.next()) {
				nricResult = result.getString("nric");
				emailResult = result.getString("emailAddress");
				mobileResult = result.getString("mobile");
				
				// comparing with the unique keys of the database to see if there is a duplicate
				if(nricResult.equals(refEmployee.getNric())) {
					 log4j.debug("registerNewEmployee: nric invalid");
					message = "The NRIC have been used before. Please try again.";
					return message;									
				}else if(emailResult.equals(refEmployee.getEmailAddress())) {
					log4j.debug("registerNewEmployee: email invalid");
					message = "The Email Address have been used before. Please try again.";
					return message;
				}else if(mobileResult.equals(refEmployee.getMobile())) {
					log4j.debug("registerNewEmployee: mobile invalid");
					message = "The Mobile have been used before. Please try again.";		
					return message;		
				}else {
					noDuplicate = true;
				}
				
			}
			
			if(noDuplicate == true) {
				
				// create query to be passed on to insert Employee into database
				String query = "insert into jeecredentials(fullName,nric,emailAddress,mobile,dob,password,department,country,qualification,address,gender,certificate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, refEmployee.getFullName());
				preparedStatement.setString(2, refEmployee.getNric());
				preparedStatement.setString(3, refEmployee.getEmailAddress());
				preparedStatement.setString(4, refEmployee.getMobile());
				preparedStatement.setDate(5, Date.valueOf(refEmployee.getDob()));
				preparedStatement.setString(6, refEmployee.getNric().substring(1, 5) + refEmployee.getMobile().substring(4));
				preparedStatement.setString(7, refEmployee.getDepartment());
				preparedStatement.setString(8, refEmployee.getCountry());
				preparedStatement.setString(9, refEmployee.getQualification());
				preparedStatement.setString(10, refEmployee.getAddress());
				preparedStatement.setString(11, refEmployee.getGender());
				
				Part filePart = refEmployee.getCertificate();
				
				InputStream inputStream = null;
				
				if(filePart != null) {
					// prints out some information for debugging 
					System.out.println(filePart.getName());
					System.out.println(filePart.getSize());
					System.out.println(filePart.getContentType());
					
					// obtains input stream of the upload file 
					// the input stream will point to a stream that contains the contents of the file 
					inputStream = filePart.getInputStream();
				}
				preparedStatement.setBinaryStream(12, inputStream, (int) filePart.getSize());
				preparedStatement.executeUpdate();
				log4j.info("New employee, " + refEmployee.getEmailAddress() + ", has been registered.");
				message = "success";	
				
			}
						
		}catch(Exception e) {
			System.out.println("Account cannot be created.");
			log4j.error("Account cannot be created.");
			System.out.println(e.getMessage());
		}
		
		return message;
		
	} // end of registerNewEmployee

	@Override
	public ArrayList<Employee> getEmployeeList() {
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select fullName,emailAddress,status from jeecredentials where role = \'user\'");
			
			// loop through to get information from sql database 
			while(result.next()) {
				Employee refEmployee = new Employee();
				refEmployee.setFullName(result.getString("fullName"));
				refEmployee.setEmailAddress(result.getString("emailAddress"));
				refEmployee.setStatus(result.getString("status"));
				employeeList.add(refEmployee);
			}
		}catch(Exception e) {
			System.out.println("Unable to get employee list");
			log4j.error("Unable to get employee list.");
			System.out.println(e.getMessage());
		}
		
		return employeeList;
		
	} // end of getEmployeeList 

	@Override
	public void updateStatus(String refUserToChangeStatus) {
		
		String status = "";
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select status from jeecredentials where emailAddress = \'" + refUserToChangeStatus + "\'");
			
			while(result.next()) {
				status = result.getString("status");
			}
			
			// change status from unlock to lock
			if(status.equals("unlock")) {
				String query = "update jeecredentials set status = \'lock\' where emailAddress = \'" + refUserToChangeStatus + "\'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.executeUpdate();	
			
			// change status from lock to unlock
			}else if(status.equals("lock")) {
				String query = "update jeecredentials set status = \'unlock\' where emailAddress = \'" + refUserToChangeStatus + "\'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.executeUpdate();
			}
		}catch(Exception e) {
			log4j.error("Unable to change status.");
			System.out.println("Cannot change status.");
		}	
		
	} // end of updateStatus

	@Override
	public void updateLastLogin(Employee refEmployee) {
		
		// get last login time from date
		java.util.Date utilDate = refEmployee.getLastLogin();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		try {
			String query = "update jeecredentials set lastLogin = \'" + sqlDate + "\' where emailAddress = \'" + refEmployee.getEmailAddress() + "\'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			log4j.error("Unable to update lastlogin.");
			System.out.println("Cannot update lastLogin");
		}

	} // end of updateLastLogin

	@Override
	public String updateEmployee(Employee refEmployee) {
		// to check if there is duplicate entry in the database
		Boolean noDuplicate = true;
		String message = "An error has occured. Please try again.";
		
		try {
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("Select emailAddress,mobile from jeecredentials");
			
			String mobileResult;
			
			while(result.next()) {
				mobileResult = result.getString("mobile");
				String emailAddress = result.getString("emailAddress");
				// comparing with the unique key of the database to see if there is a duplicate
				if(mobileResult.equals(refEmployee.getMobile()) && !emailAddress.equals(refEmployee.getEmailAddress())) {
					message = "The Mobile have been used before. Please try again.";		
					return message;		
				}else {
					noDuplicate = true;
				}
				
			}
			
			if(noDuplicate == true) {
				
				// create query to be passed on to insert Employee into database
				String query = "";
				PreparedStatement preparedStatement = null;
				
					Part filePartProfilePic = refEmployee.getProfilePic();
					
					if(filePartProfilePic != null && filePartProfilePic.getInputStream() != null) {
						query = "update jeecredentials set profilePic = ? where emailAddress = \'"+ refEmployee.getEmailAddress() + "\'";
						preparedStatement = conn.prepareStatement(query);
						
						InputStream inputStreamProfilePic = filePartProfilePic.getInputStream();
						System.out.println("CAME THERE");
						preparedStatement.setBinaryStream(1, inputStreamProfilePic, (int) filePartProfilePic.getSize());
						preparedStatement.executeUpdate();
						
					}else if(refEmployee.getProfilePicStream() != null) {
						query = "update jeecredentials set profilePic = ? where emailAddress = \'"+ refEmployee.getEmailAddress() + "\'";
						preparedStatement = conn.prepareStatement(query);
						System.out.println("CAME HERE");
						preparedStatement.setBinaryStream(1, refEmployee.getProfilePicStream());
						preparedStatement.executeUpdate();
					}
					
					Part filePartCertificate = refEmployee.getCertificate();
					
					if(filePartCertificate != null) {
						
						query = "update jeecredentials set certificate = ? where emailAddress = \'"+ refEmployee.getEmailAddress() + "\'";
						preparedStatement = conn.prepareStatement(query);
						
						InputStream inputStreamCertificate = filePartCertificate.getInputStream();
						System.out.println("CAME THERE (FILE)");
						preparedStatement.setBinaryStream(1, inputStreamCertificate, (int) filePartCertificate.getSize());
						preparedStatement.executeUpdate();
						
					}
						
						query = "update jeecredentials set fullName = ?, address = ?, mobile = ?, password = ?, qualification = ? where emailAddress = \'"+ refEmployee.getEmailAddress() + "\'";
						preparedStatement = conn.prepareStatement(query);	
						
						preparedStatement.setString(1, refEmployee.getFullName());
						preparedStatement.setString(2, refEmployee.getAddress());
						preparedStatement.setString(3, refEmployee.getMobile());
						
						if(refEmployee.getPassword() != null) {
							preparedStatement.setString(4, refEmployee.getPassword());
						}else {
							preparedStatement.setString(4, refEmployee.getHashPassword());
						}
						preparedStatement.setString(5, refEmployee.getQualification());			
						preparedStatement.executeUpdate();
						
						message = "success";	
						
						log4j.info(refEmployee.getEmailAddress() + "'s credentials have been updated.");
						
			}			
		}catch(Exception e) {
			System.out.println("Account cannot be created.");
			log4j.error("Account cannot be created.");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return message;
		
	} // end of updateEmployee
	
	@Override
	public byte[] getImage(String emailAddress) {
		
		byte[] barray = null;
		
		try {
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("Select profilePic from jeecredentials where emailAddress=\'" + emailAddress + "\'");
			
			while(result.next()) {
				barray = result.getBytes("profilePic");
			}
			System.out.println("Got the image!");
		}catch(Exception e) {
			log4j.error("Cannot get image.");
			System.out.println("Cannot get image");
		}
		return barray;		
		
	} // end of getImage
	
	@Override
	public ArrayList<Employee> getUnlockedEmployeeList() {
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select fullName,emailAddress,status from jeecredentials where role = \'user\' and status = \'unlock\'");
			
			// loop through to get information from sql database 
			while(result.next()) {
				Employee refEmployee = new Employee();
				refEmployee.setFullName(result.getString("fullName"));
				refEmployee.setEmailAddress(result.getString("emailAddress"));
				refEmployee.setStatus(result.getString("status"));
				employeeList.add(refEmployee);
			}
		}catch(Exception e) {
			log4j.error("Unable to get employee list.");
			System.out.println("Unable to get employee list");
			System.out.println(e.getMessage());
		}
		
		return employeeList;
		
	} // end of getUnlockedEmployeeList 
	
	@Override
	public ArrayList<Employee> getLockedEmployeeList() {
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			
			Statement statement = conn.createStatement();
			// To store the results from the search
			ResultSet result = statement.executeQuery("select fullName,emailAddress,status from jeecredentials where role = \'user\' and status = \'lock\'");
			
			// loop through to get information from sql database 
			while(result.next()) {
				Employee refEmployee = new Employee();
				refEmployee.setFullName(result.getString("fullName"));
				refEmployee.setEmailAddress(result.getString("emailAddress"));
				refEmployee.setStatus(result.getString("status"));
				employeeList.add(refEmployee);
			}
		}catch(Exception e) {
			log4j.error("Unable to get employee list.");
			System.out.println("Unable to get employee list");
			System.out.println(e.getMessage());
		}
		
		return employeeList;
		
	} // end of getLockedEmployeeList 

	@Override
	public String hashPassword(String password) {
		
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            
            //Get the hash's bytes
            byte[] bytes = md.digest();
            
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
        	log4j.error("Cannot hash the password.");
            e.printStackTrace();
        }
        System.out.println("The password generated is: " + generatedPassword);
		
		return generatedPassword;
		
	} // end of hashPassword 
	
} // end of EmployeeDAO
