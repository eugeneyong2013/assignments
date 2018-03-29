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
<link href="css/profile.css" rel="stylesheet" type="text/css"> 

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
									<form action="UpdateProfileController" method="post" enctype="multipart/form-data">
										<table cellpadding="8" class="table table-hover">
											<tr>
										  		<td align="right">
										  			<b>Upload profile picture: </b>
										  		</td>
										  		
										  		<td>
													<input type="file" name="updateProfilePic" value="nothing"> 
										  		</td>
										  	</tr>
											<tr>
											<tr>
												<td align="right">
													<b>Full Name: </b>
												</td>
												<td>
													<input type="text" class="form-control" name="updateFullName" value="<%=refEmployee.getFullName()%>">
													${validateNameMessage}
												</td>
											</tr>
											<tr>
												<td align="right">
													<b>Password: </b>
												</td>
												<td>
													<input type="text" class="form-control" name="updatePassword" placeholder="Enter new password">
													<input type="hidden" name="existingPassword" value="<%=refEmployee.getHashPassword()%>">
												</td>
											</tr>
											<tr>
												<td align="right">
													<b>Address: </b>
												</td>
												<td>
													<input type="text" class="form-control" name="updateAddress" value="<%=refEmployee.getAddress()%>">
											</tr>
											<tr>
												<td align="right">
													<b>Mobile: </b>
												</td>
												<td>
													<input type="number" class="form-control" name="updateMobile" value="<%=refEmployee.getMobile()%>">
													${validateMobileMessage}
												</td>
											</tr>
											<tr>
										  		<td align="right">
										  			<b>Qualification:</b>
										  		</td>
										  		
										  		<td>
										  			<select class="form-control" name="updateQualification">
										  				<option value="university">University</option>
										  				<option value="juniorCollege">Junior College</option>
										  				<option value="polytechnic">Polytechnic</option>
										  				<option value="secondary">Secondary</option>
										  				<option value="primary">Primary</option>				  				
										  			</select>
										  		</td>
										  	</tr>
											<tr>
										  		<td align="right">
										  			<b>Attach Certificate:</b>
										  		</td>
										  		
										  		<td>
													<input type="file" name="updateAttachedFile"> 
										  		</td>
										  	</tr>
											<tr>								  		
										  		<td colspan="2" align="center">
										  			<br/>
										  			<!--  register button -->
										  			<input type="hidden" name="existingMobile" value="<%=refEmployee.getMobile()%>">
										  			<input type="hidden" name="existingQualification" value="<%=refEmployee.getQualification()%>">
										  			<input type="hidden" name="updateEmailAddress" value="<%=refEmployee.getEmailAddress()%>">
				    								<button type="submit" class="btn btn-dark btn-block">Update</button>
				    								<br/>
									    			<p>${errorMessage}</p>
										  		</td>
										  	</tr>
										</table>
									</form>
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