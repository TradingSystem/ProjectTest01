<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Page</title>
</head>
<body>
<jsp:useBean id="userInfo" scope="request" class="com.mercury.beans.UserInfo"/>
<h2><font color="green"><jsp:getProperty name="userInfo" property="message"/></font></h2>
<table border="1" width="200">
	<tr>
		<th>Name</th>
		<th>Age</th>
	</tr>
	<c:forEach var="user" items="${userInfo.users}">
		<tr>
			<td>${user.name}</td>
			<td>${user.age}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>