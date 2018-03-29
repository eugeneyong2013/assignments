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

<title>Login Page</title>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style-screen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="c-wrapper" align="center">
	  <div id="myCarousel" class="carousel slide" data-ride="carousel">
	    <!-- Indicators -->
	    <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      <li data-target="#myCarousel" data-slide-to="1"></li>
	      <li data-target="#myCarousel" data-slide-to="2"></li>
	    </ol>
	
	    <!-- Wrapper for slides -->
	    <div class="carousel-inner">
	      <div class="item active">
	        <img src="css/img/slider1.jpg" alt="slider1" style="width:100%;">
	      </div>
	
	      <div class="item">
	        <img src="css/img/slider2.jpg" alt="slider2" style="width:100%;">
	      </div>
	    
	      <div class="item">
	        <img src="css/img/slider3.jpg" alt="slider3" style="width:100%;">
	      </div>
	    </div>
	
	    <!-- Left and right controls -->
	    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#myCarousel" data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right"></span>
	      <span class="sr-only">Next</span>
	    </a>
	  </div>
	</div>
	
	<%-- Create a form to get input data from user --%>
	<center>
		
		<%-- Redirects to LoginAuthenticate once submitted --%>
		<form action="LoginController" method="post">
			
			<div class="myCenter">
					
				<img class="myImg" src="css/img/oplogo.png" alt="logo" />
				
					<table>
						<tr>
							<th colspan="2">
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
							</th>
						</tr>
							
						<%-- Input user ID --%>
						<tr>
						
							<td align="right">
								<b>User ID:</b>&nbsp;
								</td>
		
							<td>							
								<input type="text" class="form-control" name="emailAddress" placeholder="Email Address" oninvalid="this.setCustomValidity('Email Address cannot be empty')" oninput="setCustomValidity('')" required="required">
							</td>
						</tr>
						
						<%-- Input user password --%>
						<tr>
							<td align="right">
								<b>Password:</b>&nbsp;
							</td>
		
							<td>
								<input type="password" class="form-control" name="password" placeholder="Password" oninvalid="this.setCustomValidity('Password cannot be empty')" oninput="setCustomValidity('')" required="required">
							</td>
						</tr>				
						
						<%-- Redirects to ForgotPassword to submit password / submit button to login --%>
						<tr>
							<td align="right" colspan="2">
								<br/>
								<input type="submit" class="btn btn-dark btn-block" role="button" value="login">
							</td>
						</tr>		
						<tr>
							<td>
								<br/>
								<a href="ForgotPassword.jsp"><font color="orange">Forgot Password?</font></a>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<%-- Print error message --%>
								<font size="2">${errorMessage}</font>
								<br/>
							</td>
						</tr>		
					</table>		
			</div>
		</form>	
	</center>
	
	<div class="myFooter">
		<p><font color="orange">Copyright (c) 2018. <b>Optimum Solutions</b></font></p>
	</div>
	
</body>
</html>