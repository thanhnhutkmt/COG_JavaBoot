<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Form</h1>
	<form action="process-login.struts2" method="post">
		<table border="1px solid black">
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username"></td>				
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>				
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="login"></td>							
			</tr>			
		</table>
	</form>
	
	<%
		String message = (String) request.getAttribute("message");
	if (!(message == null)) {
		out.println(message);
	} else {
		message = "";
		out.println(message);
	}
			
	%>
	
</body>
</html>