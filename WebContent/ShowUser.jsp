<%@page import="cn.me.model.SUser"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<SUser> empList = (List) request.getAttribute("list");
	%>

	<table border="1" align="center">
		<caption>用户列表</caption>
		<tr>
			<td>姓名</td>
			<td>年龄</td>
			<td>性别</td>
		</tr>
		<%
			for (int i = 0; i < empList.size(); i++) {
				SUser user = empList.get(i);
		%>
		<tr>
			<td><%=user.getUname()%></td>
			<td><%=user.getAge()%></td>
			<td><%=user.getSex()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>