<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List NoticeBoard here</title>
</head>
<body>
<h2>공지사항</h2>
<hr>
<table class="table table-bordered table-hover">
<tr>
	<th width="30px">글번호</th>
	<th width="50%">제 목</th>
	<th  width="100px">등록일</th>
</tr>
<c:forEach var="n" items="${list }">
	<tr>
		<td>${n.p_no }</td>
		<td><a href="detailNotice.do?p_no=${n.p_no }">${n.p_title }</a></td>
		<td>${n.p_regdate }</td>
	</tr>
</c:forEach>
</table>
<c:if test="${lm.g_num ==40}">
	<a href="insertNotice.do" class="btn btn-default">글쓰기</a>
</c:if>
${pageStr }
</body>
</html>