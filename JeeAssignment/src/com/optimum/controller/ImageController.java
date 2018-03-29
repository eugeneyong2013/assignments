package com.optimum.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.optimum.dao.EmployeeDAO;
import com.optimum.dao.EmployeeDAOImpl;

@WebServlet("/ImageController")
@MultipartConfig(maxFileSize = 16177215)

public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO refEmployeeDAO;
	
	public ImageController() {
		refEmployeeDAO = new EmployeeDAOImpl();
	} // end of constructor 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String emailAddress = request.getParameter("emailAddress");
		OutputStream img;
		
		// getting the image file and printing it to the screen 
		byte[] barray = refEmployeeDAO.getImage(emailAddress);
		response.setContentType("image/png");
		img = response.getOutputStream();
		img.write(barray);
		img.flush();
		img.close();			
		
	} // end of doGet 
} // end of ImageController
