package com.optimum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;
import com.optimum.pojo.Employee;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Employee refEmployee;
	private EmployeeDAO refEmployeeDAO;
	
	public ForgotPasswordController() {
		refEmployeeDAO = new EmployeeDAOImpl();
		refEmployee = new Employee();
		
	} // end of constructor 
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the information from LoginPage and set it into a Employee
		refEmployee.setEmailAddress(request.getParameter("forgotPasswordEmailAddress"));
		
		if(refEmployeeDAO.forgotPasswordAuthenticate(refEmployee)) {
			
			// send success message back to the forgot password screen
			String successMessage = "<html><body><font color='green'>Successfully Submitted to Email Address</font></body></html>";
			request.setAttribute("successMessage", successMessage);
			request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);		

		 }else {
			 
			// send error message back to the login screen if authentication failed  
			String errorMessage = "<html><body><font color='red'>Invalid Email Address or Locked</font></body></html>";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
		 }
		
	} // end of service 

} // end of ForgotPasswordController
