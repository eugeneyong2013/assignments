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


@WebServlet("/RegisterController")
//maximum upload file size is up to 16mb
@MultipartConfig(maxFileSize = 16177215)

public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Employee refEmployee;
	private EmployeeDAO refEmployeeDAO;
	
	public RegisterController() {
		refEmployeeDAO = new EmployeeDAOImpl();
		refEmployee = new Employee();
		
	} // end of constructor 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the information from CreateNewEmployeePage and set it into a Employee
		refEmployee.setFullName(request.getParameter("fullName"));
		refEmployee.setNric(request.getParameter("nric"));
		refEmployee.setGender(request.getParameter("gender"));
		refEmployee.setAddress(request.getParameter("address"));
		refEmployee.setCountry(request.getParameter("country"));
		refEmployee.setQualification(request.getParameter("qualification"));
		refEmployee.setDepartment(request.getParameter("department"));
		refEmployee.setEmailAddress(request.getParameter("emailAddress"));
		refEmployee.setMobile(request.getParameter("mobile"));
		refEmployee.setDob(request.getParameter("dob"));
		
		Part filePart = request.getPart("attachedFile");
		refEmployee.setCertificate(filePart);
		
		String message = "An error has occured";
		
		// Validate full name 
		boolean validateName = refEmployee.getFullName().trim().matches("^[a-zA-Z\\s]+");
		
		// validate nric 
		boolean validateNric = refEmployee.getNric().trim().matches("\\p{Upper}\\d{7}\\p{Upper}");
		
		// validate country 
		boolean validateCountry = refEmployee.getCountry().trim().matches("^[a-zA-Z\\s]+");
		
		// validate email 
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(refEmployee.getEmailAddress().trim());
        boolean validateEmail = m.matches();
        
        // validate mobile
		String mPattern = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{8})$";
		Pattern pattern = Pattern.compile(mPattern);
		Matcher match = pattern.matcher(refEmployee.getMobile().trim());
		boolean validateMobile = match.matches();
        
        
		
		// full name not valid message
		if(validateName == false) {
			String validateNameMessage = "<font color='red' size=\"2\">Full name field is not valid</font>";
			request.setAttribute("validateNameMessage", validateNameMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);	
		
		// nric not valid message
		}else if(validateNric == false) {
			String validateNricMessage = "<font color='red' size=\"2\">Nric field is not valid</font>";
			request.setAttribute("validateNricMessage", validateNricMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);	
		}
		
		// country not valid message
		else if(validateCountry == false) {
			String validateCountryMessage = "<font color='red' size=\"2\">Country field is not valid</font>";
			request.setAttribute("validateCountryMessage", validateCountryMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);	
		}
		
		//  email not valid message 
		else if(validateEmail == false) {
			String validateEmailMessage = "<font color='red' size=\"2\">Email address field is not valid</font>";
			request.setAttribute("validateEmailMessage", validateEmailMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);	
		}
		
		// mobile not valid message
		else if(validateMobile == false) {
			String validateMobileMessage = "<font color='red' size=\"2\">Mobile field is not valid</font>";
			request.setAttribute("validateMobileMessage", validateMobileMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);	
		}
		
		// if validation is all correct, send to register		
		message = refEmployeeDAO.registerNewEmployee(refEmployee);
		
		if(message.equals("success")) {
			
			// send success message back to the admin home page screen
			String successMessage = "<html><body><font color='green'>Successfully registered, password has been sent to email.</font></body></html>";
			request.setAttribute("successMessage", successMessage);
			request.getRequestDispatcher("AdminPage.jsp").forward(request, response);		
			
		}else {
			
			// send error message back to the registration screen if registration failed  
			String errorMessage = "<html><body><font color='red'>" + message + "</font></body></html>";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("CreateNewEmployeePage.jsp").forward(request, response);
		}
		
	} // end of service 

} // end of RegisterController 
