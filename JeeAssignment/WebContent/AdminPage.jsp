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

<%@ page import="com.optimum.pojo.Employee" %>

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
			<table cellpadding="5">	
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
				
				
					<td>	
								<!-- To enable scrolling -->
								<br/>
								<br/>
								<br/>
								<br/>
								<div class="ScrollStyle">
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  	<%
									  		// when new employee is successfully registered, this message will be printed
										    if(request.getAttribute("successMessage") != null){
										%>
										    	<p>${successMessage}</p>
										<%    								        
											} else {
										%>
											 Nothing to display here
										<%
											}
										%>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
									  <br/>
								</div>
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