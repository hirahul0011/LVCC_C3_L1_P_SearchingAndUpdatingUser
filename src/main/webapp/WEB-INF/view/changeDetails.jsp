<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">	
	<h1>Please change the details</h1>
	<form action="changeDetailsValidation" method="post">
	<table>		
		<tr>			
			<td>First Name: </td>
			<td><input type="text" name="userId" placeholder="${userId}" 
			value="${userId}" disabled>
			<input type="hidden" name="userId" placeholder="${userId}" 
			value="${userId}"></td>						
		</tr>
		<tr>
			<td>Password: </td>
			<td><input type="text" name="password" placeholder="${password}"></td>
		</tr>
		<tr>
			<td>First Name: </td>
			<td><input type="text" name="firstName" placeholder="${firstName}"></td>			
		</tr>
		<tr>			
			<td>Last Name: </td>
			<td><input type="text" name="lastName" placeholder="${lastName}"></td>			
		</tr>
		<tr>			
			<td>Age: </td>
			<td><input type="number" name="age" placeholder="${age}"></td>			
		</tr>
		<tr>			
			<td>Gender: </td>
			<td><input type="text" name="gender" placeholder="${gender}" 
			value="${gender}" disabled>
			<input type="hidden" name="gender" placeholder="${gender}" 
			value="${gender}"></td>			
		</tr>		
	</table><br>
	<span style="color:red">${errorMessage}</span><br/>	
	<table>							
		<tr>
			<input type="submit" value="Submit"/>			
		</tr>		
	</table>
	</form>
	</div>

</body>
</html>