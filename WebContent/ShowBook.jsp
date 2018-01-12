<%@page import="cn.me.model.SUser"%>
<%@page import="cn.me.model.Book"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
.login {
	padding-right: 180px;
	padding-top: 40px;
	font-size: 30px;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<%
		List<Book> bList = (List) request.getAttribute("blist");
		SUser o = (SUser) session.getAttribute("login");
	%>

	<p class="login" align="right">
		<a href="login.jsp"><%=o == null ? "登陆" : o.getUname()%></a> <a
			href="#">注册</a>
	</p>
	<table border="1" align="center">
		<caption>图书列表</caption>
		<tr>

			<td align="right" colSpan=5><a href="BookServlet?param=cart">添加到购物车</a></td>
		</tr>
		<tr>
			<td><input type="checkbox" value="xuanze">全选</td>
			<td>书名</td>
			<td>书价</td>
			<td>图片</td>
			<td>操作</td>
		</tr>
		<%
			for (int i = 0; i < bList.size(); i++) {
				Book book = bList.get(i);
		%>

		<tr>
			<td><input type="checkbox" value="xuanze"></td>
			<td><%=book.getbName()%></td>
			<td><%=book.getbPrice()%></td>
			<td><img src="<%=book.getbImg()%>"></td>
			<td><a href="BookServlet?param=del&delParam=<%=book.getBid()%>">删除</a>
				|<a href="BookServlet?param=edit&editParam=<%=book.getBid()%>">修改</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="addBook.jsp">添加图书</a>
</body>
</html>