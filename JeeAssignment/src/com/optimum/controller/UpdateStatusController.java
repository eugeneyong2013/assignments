package com.optimum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;

@WebServlet("/UpdateStatusController")
public class UpdateStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO refEmployeeDAO;
	
	public UpdateStatusController() {
		refEmployeeDAO = new EmployeeDAOImpl();
	} // end of constructor 

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		// get the information 
		String page = request.getParameter("page");
		String emailAddress = request.getParameter("employeeEmailAddress");
		
		// change the status of the employee if the email is valid and set sessions needed for repopulating the tables
		if(emailAddress != null) {
			refEmployeeDAO.updateStatus(emailAddress);	
			session.setAttribute("currentSessionEmployeeList", refEmployeeDAO.getEmployeeList());
			session.setAttribute("currentSessionUnlockedEmployeeList", refEmployeeDAO.getUnlockedEmployeeList());
			session.setAttribute("currentSessionLockedEmployeeList", refEmployeeDAO.getLockedEmployeeList());
		}
		
		// redirect page to the page where the button was clicked
		if(page.equals("ViewEmployeeListPage")) {
			response.sendRedirect("ViewEmployeeListPage.jsp");
		}else if(page.equals("ViewRequestStatusPage")){
			response.sendRedirect("ViewRequestStatusPage.jsp");
		}
		
	} // end of service 

} // end of UpdateStatusController
