<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Student Information</h1>
	<table border="1px solid black">
		<tr>
			<th>Student ID</th>
			<th>Student Name</th>
			<th>Address</th>
		</tr>
		<tr>
			<td>${student.studentID}</td>
			<td>${student.studentName}</td>
			<td>${student.address}</td>
		</tr>
	</table>
</body>
</html>