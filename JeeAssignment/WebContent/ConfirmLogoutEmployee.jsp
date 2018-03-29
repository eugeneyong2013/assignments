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

<title>Logout Page</title>

</head>
<body>
	<center>
		<div class="myLogout">
		<table cellpadding="2">
			<tr>
				<td colspan="2">
					<br/>
					<br/>
					<b>Wish to Confirm Logout?</b>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					<br/>
					<!-- No button for logout -->
					<form method="get" action="EmployeePage.jsp">
    					<button class="btn btn-dark btn-sm btn-block" type="submit">No</button>
					</form>
				</td>	
			</tr>
			<tr>
				<td align="center">
					<!-- yes button for logout -->
					<form method="get" action="TimeSpent.jsp">
    					<button class="btn btn-dark btn-sm btn-block" type="submit">Yes</button>
					</form>
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