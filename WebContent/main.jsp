<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원관리 </title>
</head>

<body>
	<h2>회원 전용 페이지 </h2>
	<form action="logout.do">
		안녕하세요. ${loginUser.name }(${loginUser.userid })님 </br>
		<input type="submit" value="logout"> &nbsp;&nbsp;
		<input type="button" value="edit info" onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'">
	</form>
</body>

</html>