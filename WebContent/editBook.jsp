<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="cn.me.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<%
	Book book = (Book) request.getAttribute("book");
%>
</head>

<body>
	<p>修改图书页面</p>
	<!--修改之后 就将数据传输到bookservlet的update里面处理一下  -->
	<form action="BookServlet?param=update" method="post">
		<table>
			<tr>
				<td>图书信息</td>
				<td>修改为</td>
			</tr>
			<tr>
				<td>图书名字</td>
				<td><input type="text" name="bName" value="<%=book.getbName()%>"></td>
			</tr>
			<tr>
				<td>图书价格</td>
				<td><input type="text" name="bPrice" value="<%=book.getbPrice()%>"></td>
			</tr>
			<tr>
				<td>图书照片</td>
				<td><input type="text" name="bImg" value="<%=book.getbImg()%>"></td>
			</tr>
			<tr>
				<td>图书ID</td>
				<td><input type="text" name="bId" value="<%=book.getBid()%>"></td>
			</tr>
		</table>
		
		<input type="submit" value="保存修改">
	</form>
</body>
</html>