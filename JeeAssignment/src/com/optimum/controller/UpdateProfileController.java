package com.optimum.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;
import com.optimum.pojo.Employee;


@WebServlet("/UpdateProfileController")
//maximum upload file size is up to 16mb
@MultipartConfig(maxFileSize = 16177215)

public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Employee employeeRef;
	private EmployeeDAO refEmployeeDAO;
	
	public UpdateProfileController() {
		refEmployeeDAO = new EmployeeDAOImpl();
		employeeRef = new Employee();
		
	} // end of constructor 

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get all the inputs and set it into an employee object
		employeeRef.setFullName(request.getParameter("updateFullName"));
		employeeRef.setAddress(request.getParameter("updateAddress"));
		employeeRef.setQualification(request.getParameter("updateQualification"));
		employeeRef.setMobile(request.getParameter("updateMobile"));
		employeeRef.setEmailAddress(request.getParameter("updateEmailAddress"));
		
		// if no input is given for password, reuse old password
		if(request.getParameter("updatePassword").trim().isEmpty() || request.getParameter("updatePassword") == null || request.getParameter("updatePassword").equals("Enter new password")) {
			employeeRef.setHashPassword(request.getParameter("existingPassword"));
		}else {
			employeeRef.setPassword(request.getParameter("updatePassword"));
		}
		
		// if the mobile is the same, reuse the old mobile
		String existingMobile = request.getParameter("existingMobile");
		if(existingMobile.equals(employeeRef.getMobile())) {
			employeeRef.setMobile(existingMobile);
		}
		
		// if qualification remains the same, reuse the old qualifications 
		if(request.getParameter("updateQualification") != null && !request.getParameter("updateQualification").isEmpty()) {
			employeeRef.setQualification(request.getParameter("updateQualification"));
		}else {
			employeeRef.setQualification(request.getParameter("existingQualification"));
		}
		
		// get profile picture
		Part filePart2 = request.getPart("updateProfilePic");
		employeeRef.setProfilePic(filePart2);
		
		// get certificate 
	    Part filePart = request.getPart("updateAttachedFile");
		employeeRef.setCertificate(filePart);
		
		String message = "An error has occured";
        
		if(employeeRef.getFullName() != null && !employeeRef.getFullName().isEmpty()) {
			
			// Validate full name 
			boolean validateName = employeeRef.getFullName().trim().matches("^[a-zA-Z\\s]+");
			
			// full name not valid message
			if(validateName == false) {
				String validateNameMessage = "<font color='red' size=\"2\">Full name field is not valid</font>";
				request.setAttribute("validateNameMessage", validateNameMessage);
				request.getRequestDispatcher("EditProfilePage.jsp").forward(request, response);	
			}
		}
		
		if(employeeRef.getMobile() != null && !employeeRef.getMobile().isEmpty()) {
			
			// validate mobile
			String mPattern = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{8})$";
			Pattern pattern = Pattern.compile(mPattern);
			Matcher match = pattern.matcher(employeeRef.getMobile().trim());
			boolean validateMobile = match.matches();
			
			// mobile not valid message
			if(validateMobile == false) {
				String validateMobileMessage = "<font color='red' size=\"2\">Mobile field is not valid</font>";
				request.setAttribute("validateMobileMessage", validateMobileMessage);
				request.getRequestDispatcher("EditProfilePage.jsp").forward(request, response);	
			}
		}
		
		// if validation is all correct, send to register		
		message = refEmployeeDAO.updateEmployee(employeeRef);
		
		if(message.equals("success")) {
			
			// send success message back to the admin home page screen
			String successMessage = "<html><body><font color='green'>Update successful! Changes will take place after relog</font></body></html>";
			request.setAttribute("successMessage", successMessage);
			request.getRequestDispatcher("EmployeePage.jsp").forward(request, response);		
			
		}else {
			
			// send error message back to the registration screen if registration failed  
			String errorMessage = "<html><body><font color='red'>" + message + "</font></body></html>";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("EditProfilePage.jsp").forward(request, response);
		}
	} // end of service

} // end of UpdateProfileController
