<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원관리</title>
	<script type="text/javascript" src="script/member.js"></script>
</head>

<body>
	<h2> login </h2>
	<form action="login.do" method="post" name="frm">
		<div>
			id: <input type="text" name="userid" value="${userid}"></br>
			pw: <input type="password" name="pwd""></br>
		</div>
		
		<div>
			<input type="submit" value="login" onclick="return loginCheck()">&nbsp;&nbsp;
			<input type="reset" value="cancel">&nbsp;&nbsp;
			<input type="button" value="register" onclick="location.href='join.do'">
		</div>
	</form>


</body>
</html>