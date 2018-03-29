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

<%@ page import="java.util.Date" %>
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
				<td colspan="2" align="center">
					<br/>
					<br/>
					<b>You have logged on for:</b>
					<% 
						// to compare between previous last login time and new login time and display the difference
						Date oldDate = (Date) session.getAttribute("currentSessionLoginTime");
						Date newDate = new Date(session.getLastAccessedTime());

						long diff = newDate.getTime() - oldDate.getTime();
						long diffSeconds = diff / 1000 % 60;
						long diffMinutes = diff / (60 * 1000) % 60;
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffDays = diff / (24 * 60 * 60 * 1000);
					%>
					<p align="center"><%=diffHours%> Hours, <%=diffMinutes%> Minutes, <%=diffSeconds%> Seconds</p>
					<br/>
				</td>
			</tr>
			
			<tr>
				<td align="center">
					<!-- yes button for logout -->
					<form method="get" action="Logout.jsp">
    					<button class="btn btn-dark btn-sm btn-block" type="submit">Ok</button>
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