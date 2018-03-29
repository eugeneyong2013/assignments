package com.optimum.controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;
import com.optimum.pojo.Employee;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Employee refEmployee;
	private EmployeeDAO refEmployeeDAO;
	
	public LoginController() {
		refEmployeeDAO = new EmployeeDAOImpl();
		refEmployee = new Employee();
		
	} // end of constructor 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		// get the information from LoginPage and set it into a Employee
		refEmployee.setEmailAddress(request.getParameter("emailAddress"));
		refEmployee.setPassword(request.getParameter("password"));	
		Employee e = new Employee();
		
		// pass Employee to authenticate with sql database
		e = refEmployeeDAO.loginAuthenticate(refEmployee);
		
		if(e != null) {
			
			// to get and insert last login time into Employee's details 
			Date date = new Date(session.getLastAccessedTime());
			Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
			format.format(date);
			e.setLastLogin(date);
			session.setAttribute("currentSessionLoginTime", date);
			refEmployeeDAO.updateLastLogin(e);
			
			// set all the sessions to be use for later functions 
			session.setAttribute("currentSessionEmployee", e);
			session.setAttribute("currentSessionEmployeeProfilePic", e.getProfilePicStream());
			session.setAttribute("currentSessionEmployeeCertificate", e.getCertificateStream());
			session.setAttribute("currentSessionEmployeeList", refEmployeeDAO.getEmployeeList());
			session.setAttribute("currentSessionUnlockedEmployeeList", refEmployeeDAO.getUnlockedEmployeeList());
			session.setAttribute("currentSessionLockedEmployeeList", refEmployeeDAO.getLockedEmployeeList());
			
			if(e.getRole().equals("admin")) {
				// redirect to welcome screen after authentication
				response.sendRedirect("AdminPage.jsp");
			}else if(e.getRole().equals("user")) {
				// redirect to welcome screen after authentication
				response.sendRedirect("EmployeePage.jsp");
			}
			
		 }else {	 
			// send error message back to the login screen if authentication failed  
			String errorMessage = "<html><body><font color='red'>Invalid Login and/or Password</font></body></html>";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
		 }
		    	
	} // end of post
	
} // end of EmployeeController 
