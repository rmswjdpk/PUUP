<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-bordered table-hover">
<tr>
	<th>게시판</th>
	<th>작성글 개수</th>
	<th>댓글 개수</th>
</tr>
<c:forEach var="b" items="${list }">
<tr>
	<td>${b.B_NAME }</td>
	<td>${b.POST_COUNT }</td>
	<td>${b.REPLY_COUNT }</td>
</tr>
</c:forEach>
</table>
</body>
</html>