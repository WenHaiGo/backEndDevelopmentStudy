<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 就是要注意get不能提交参数  因为处理登陆请求属于user操作，所以指向userservlet -->
	<form action="SUserServlet" >
		user<input  type="text" name="user"> password<input type="password" name = "pwd">
		<input  type ="submit">
		
	</form>
</body>
</html>