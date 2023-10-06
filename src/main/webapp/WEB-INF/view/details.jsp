<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<h1>${userFirstName}! Below are your personal details</h1>
	<!-- <h1>As per your submission below are the available flights</h1>	 -->
	<table>
		<tr>
			<td><b>userId</b></td>
			<td><b>password</b></td>
			<td><b>firstName</b></td>
			<td><b>lastName</b></td>
			<td><b>age</b></td>
			<td><b>gender</b></td>			
			<td></td>			
		</tr>
		<%-- <c:forEach items="${users}" var="user"> --%>			
			<tr>
			<td> ${user.userId}</td>
			<td> ${user.password}</td>
			<td> ${user.firstName}</td>
			<td> ${user.lastName}</td>
			<td> ${user.age}</td>
			<td> ${user.gender}</td>						
			<td><a href="changeDetails?
			userId=${user.userId}
			&password=${user.password}
			&firstName=${user.firstName}
			&lastName=${user.lastName}
			&age=${user.age}
			&gender=${user.gender}">Change Details</a></td>
			</tr>
		<%-- </c:forEach> --%>
	</table>
	</div>

</body>
</html>