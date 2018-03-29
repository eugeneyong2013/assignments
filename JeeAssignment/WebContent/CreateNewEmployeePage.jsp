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

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style-screen.css" rel="stylesheet" type="text/css"> 
<%@ page import="com.optimum.pojo.Employee" %>

<title>Admin Page</title>

</head>
<body>
	<center>
		<br/>
		<div class="myScreen">	
		<!-- Logo -->
		<img class="myImg02" src="css/img/oplogo.png" alt="logo" />
		<table cellpadding="10">
			<tr>
				<td style="text-align:right; min-width: 260px; padding-left: 30px">
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
								
								<!-- All the fields needed for registration -->
								<form method="post" action="RegisterController" enctype="multipart/form-data">
						
									  <center>
										  <table cellpadding="8">
										  <br/>										  
										  	<tr>
										  		<td align="right">
										  			<b>Full Name:</b>
										  		</td>
										  			
										  		<td>
										  			<input type="text" class="form-control" name="fullName" placeholder="Enter your full name" oninvalid="this.setCustomValidity('Full name cannot be empty')" oninput="setCustomValidity('')"required="required">
										  			${validateNameMessage}
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>NRIC:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="text" class="form-control" name="nric" placeholder="Enter your nric (Uppercase)" required="required">
										  			${validateNricMessage}
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Gender:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="radio"  class="radio-inline" id="genderMale" name="gender" value="male">
										  			<label for="genderMale">Male</label> &nbsp;&nbsp;
										  			<input type="radio"  class="radio-inline" id="genderFemale" name="gender" value="female">
										  			<label for="genderMale">Female</label>
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Address:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="text" class="form-control" name="address" placeholder="Enter your address" required="required">
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Country:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="text" class="form-control" name="country" placeholder="Enter your country" required="required">
										  			${validateCountryMessage}
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Qualification:</b>
										  		</td>
										  		
										  		<td>
										  			<select class="form-control" name="qualification">
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
													<input type="file" name="attachedFile" required="required"> 
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Department:</b>
										  		</td>
										  		
										  		<td>
										  			<select class="form-control" name="department">
										  				<option value="hr">Human Resource</option>
										  				<option value="finance">Finance</option>
										  				<option value="administration">Administration</option>
										  				<option value="development">Development</option>	
										  				<option value="pr">Public Relations</option>						  				
										  			</select>
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Email Address:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="email" class="form-control" name="emailAddress" placeholder="Enter your email address" required="required">
										  			${validateEmailMessage}
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>mobile:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="number" class="form-control" name="mobile" placeholder="Enter Your mobile number" required="required">
										  			${validateMobileMessage}
										  		</td>
										  	</tr>
										  	
										  	<tr>
										  		<td align="right">
										  			<b>Date of Birth:</b>
										  		</td>
										  		
										  		<td>
										  			<input type="date" class="form-control" name="dob" required="required">
										  		</td>
										  	</tr>
										  	
										  	<tr>								  		
										  		<td colspan="2" align="center">
										  			<br/>
										  			<!--  register button -->
				    								<button type="submit" class="btn btn-dark btn-block">Register</button>
				    								<br/>
									    			<p>${errorMessage}</p>
										  		</td>
										  	</tr>
										  </table>
									  </center>
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