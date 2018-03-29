<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- To prevent user from clicking back -->
<script>
	history.pushState(null, null, '');
	window.addEventListener('popstate', function(event) {
	 history.pushState(null, null, '');
	});
</script>

<%@ page import="com.optimum.dao.EmployeeDAO" %>
<%@ page import="com.optimum.dao.EmployeeDAOImpl" %>
<%@ page import="com.optimum.pojo.Employee" %>
<%@ page import="java.util.ArrayList" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style-screen.css" rel="stylesheet" type="text/css"> 

<title>Admin Page</title>

</head>
<body>
	<center>
		<br/>
		<div class="myScreen">		
		<!-- Logo -->
		<img class="myImg02" src="css/img/oplogo.png" alt="logo" />
		<table cellpadding="10" >
			<tr>
				<td style="text-align:right ;min-width: 260px; padding-left: 30px">
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						Welcome <b>Admin</b>
						<br/>
						Last Login Date and Time 
						<br/>
						<%
							Employee refEmployee = (Employee) session.getAttribute("currentSessionEmployee"); 	
						%>
						<p><mark><% out.print(refEmployee.getLastLogin().toString()); %></mark></p>
						
						<!--  logout button -->
						<form method="get" action="ConfirmLogout.jsp" align="right">
				    		<button type="submit" class="btn btn-dark">Logout</button>
						</form>
						
						<br/>
						<br/>
						<a class="btn btn-warning btn-block" href="CreateNewEmployeePage.jsp">Create New Employee</a>
						<br/>
						<a class="btn btn-warning btn-block" href="ViewEmployeeListPage.jsp">View Employee List</a>
						<br/>
						<a class="btn btn-warning btn-block" href="ViewRequestStatusPage.jsp">View Request Status</a>				
						<br/>
						<br/>											
				</td>
			
				<!-- When employee clicks status button to change between lock and unlock -->
				<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script> 
				<script> 
					$(document).ready(function() {  				
					   $('input[type=radio]').change(function(){ 			
					        $('form').submit(); 			
					   }); 				
					  }); 
				</script> 
		
				<td>	
							<!-- To enable scrolling -->
							<br/>
							<br/>
							<br/>
							<br/>
							<div class="ScrollStyle">
									  <center>
										  <table cellpadding="8" class="table table-hover">			
										  	<!-- To print out the list of employees -->
										  	<tr><td><b>No.</b></td><td><b>Full Name</b></td><td><b>Email Address</b></td><td><b>Status</b></td><td><b>Lock?</b></td></tr>
											
											<% 
												int checker = 0;
												int numOfRecords = 0;
												int numOfPages = 0;
												int valuesLimit = 0;
												int pageNo = 0;
												int valueStart = 0;
												
												String valuesLimitString = request.getParameter("valuesLimit");
												String pageNoString = request.getParameter("pageNo");
												
												if(pageNoString == null){
													pageNo = 1;
												}else{
													pageNo = Integer.parseInt(pageNoString);
												}
												
												if(pageNo == 1){
													valueStart = 0;
												}else if(pageNo > 1){
													valueStart = (pageNo - 1) * 5;
												}				
												
												if(valuesLimitString == null){
													valuesLimit = 5;
												}else{
													valuesLimit = Integer.parseInt(valuesLimitString);
												}
												
												EmployeeDAO refEmployeeDAO = new EmployeeDAOImpl();
												ArrayList<Employee> employeeList = (ArrayList<Employee>) session.getAttribute("currentSessionUnlockedEmployeeList");
												if(employeeList == null || employeeList.isEmpty()){
													%>
														<tr><td align="center" colspan="5">There is currently no employees</td></tr>
													<% 
												}else{
													numOfRecords = employeeList.size();
													numOfPages = numOfRecords/5;												
												
													if(numOfRecords%5 != 0){
														numOfPages += 1;
													}
													if(valuesLimit == 0){
														valuesLimit = numOfRecords;
													}
													if(pageNo == numOfPages){
														valuesLimit = numOfRecords;
													}
													
													%>
													<form action="UpdateStatusController" method="get">
													<% 
													for(int i = valueStart; i < valuesLimit; i++){
														Employee e = employeeList.get(i);
														if(e.getStatus().equals("unlock")){
															checker += 1;														
														%>
															<!-- Print out each employee -->
															<tr>
																<td>
																	<%=i+1%>
																</td>
																<td>
																	<%=e.getFullName()%>
																</td>
																<td>
																	<%=e.getEmailAddress()%>
																</td>
																<td>
																	<font color="green"><b><%=e.getStatus()%></b></font>
																</td>
																<td align="center">
																		<input type="radio" class="radio-inline" name="employeeEmailAddress" value="<%=e.getEmailAddress()%>">															
																</td>
															</tr>
															
														<% 
														}

													}
													
													%><input type="hidden" name="page" value="ViewEmployeeListPage"></form><% 
															
													if(checker == 0){
														%>
															<tr><td colspan="5" align="center">There is currently no employees</td></tr>
														<% 											
													}else{
														checker = 0;
													}									
												}
											%>
										  </table>
									  </center>								  
							</div>
				</td>	
			</tr>	
			<tr>
				<td>
				</td>
				<td>
					<!-- Pagination -->
					<nav aria-label="Page navigation example">
  						<ul class="pagination justify-content-center">
    						<li class="page-item">
      							<a class="page-link" href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							        <span class="sr-only">Previous</span>
      							</a>
    						</li>
    						<% for(int i = 1; i <= numOfPages; i++){ %>
    							<li class="page-item"><a class="page-link" href="ViewEmployeeListPage.jsp?pageNo=<%=i%>&valuesLimit=<%=i*5%>"><%=i%></a></li>
    						<% } %>
    						<li class="page-item">
      							<a class="page-link" href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							        <span class="sr-only">Next</span>
      							</a>
    						</li>
  						</ul>
					</nav>
				</td>
			</tr>
		</table>
		</div>
	</center>
	
	<div class="myFooter">
		<p><font color="orange">Copyright (c) 2018. <b>Optimum Solutions</b></font></p>
	</div>
	
</body>
</html>