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

<title>Employee Page</title>

</head>
<body>
	<center>
		<%
			Employee refEmployee = (Employee) session.getAttribute("currentSessionEmployee"); 	
		%>
		<br/>
		<div class="myScreen">	
			<img class="myImg02" src="css/img/oplogo.png" alt="logo" />
			<table cellpadding="5">	
				<tr>
					<td style="text-align:right ;min-width: 260px; padding-left: 30px">	
							<br/>
							<br/>
							<br/>
							<br/>
							Welcome <b><%=refEmployee.getFullName()%></b>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<% if(refEmployee.getProfilePicStream() == null){ %>
								<img class="profile" src="css/img/blank.png" alt="logo" />
							<%}else{ %>
								<img class="profile" src="ImageController?emailAddress=<%=refEmployee.getEmailAddress()%>" alt="logo" />
							<%} %>
							<br/>
							Last Login Date and Time 
							<br/>
							<p><mark><% out.print(refEmployee.getLastLogin().toString()); %></mark></p>
							
							<!--  logout button -->
							<form method="get" action="ConfirmLogoutEmployee.jsp" align="right">
					    		<button type="submit" class="btn btn-dark">Logout</button>
							</form>
							
							<br/>
							<br/>
							<a class="btn btn-warning btn-sm btn-block" href="EditProfilePage.jsp">Edit Profile</a>
							<br/>
							<a class="btn btn-warning btn-sm btn-block" href="ViewProfilePage.jsp">View Profile</a>
							<br/>		
							<br/>
							<br/>										
					</td>
				
				
					<td>	
								<br/>
								<br/>
								<!-- To enable scrolling -->
								<div class="ScrollStyle">
									<table cellpadding="8" class="table table-hover">
										<tr>
											<td align="right">
												<b>EmployeeID: </b>
											</td>
											<td>
												<%=refEmployee.getEmployeeId()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Full Name: </b>
											</td>
											<td>
												<%=refEmployee.getFullName()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Email Address: </b>
											</td>
											<td>
												<%=refEmployee.getEmailAddress()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>NRIC: </b>
											</td>
											<td>
												<%=refEmployee.getNric()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Gender: </b>
											</td>
											<td>
												<%=refEmployee.getGender()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Address: </b>
											</td>
											<td>
												<%=refEmployee.getAddress()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Date of Birth: </b>
											</td>
											<td>
												<%=refEmployee.getDob()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Country: </b>
											</td>
											<td>
												<%=refEmployee.getCountry()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Department: </b>
											</td>
											<td>
												<%=refEmployee.getDepartment()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Mobile: </b>
											</td>
											<td>
												<%=refEmployee.getMobile()%>
											</td>
										</tr>
										<tr>
											<td align="right">
												<b>Qualification: </b>
											</td>
											<td>
												<%=refEmployee.getQualification()%>
											</td>
										</tr>
									</table>
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