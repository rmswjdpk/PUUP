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
<h2>자유게시판</h2>
<hr>
<table class="table table-bordered table-hover">
<tr>
	<th width="50px">글번호</th>
	<th width="50%">제 목</th>
	<th width="100px">작성자</th>
	<th width="100px">등록일</th>
	<th width="100px">조회수</th>
</tr>
<c:forEach var="p" items="${list }">
<tr>
	<td>${p.p_no }</td>
	<td><a href="detailFree.do?p_no=${p.p_no }">${p.p_title }</a></td>
	<td>${p.m_id }</td>
	<td>${p.p_regdate }</td>
	<td>${p.p_hit }</td>
</tr>
</c:forEach>
</table>
<a href="insertFree.do" class="btn btn-default">글쓰기</a>
${pageStr }
</body>
</html>