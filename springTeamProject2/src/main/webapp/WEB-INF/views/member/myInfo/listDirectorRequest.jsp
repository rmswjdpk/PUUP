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
	<h2>디렉터 신청 목록</h2>
	<hr>
	<table class="table table-bordered table-hover">
		<tr>
			<th>글번호</th>
			<th>분류</th>
			<th>제목</th>
			<th>등록일</th>
			<th>마감일</th>
			<th>조회수</th>
			<th>찬성수</th>
			<th>반대수</th>
		</tr>
		
		<c:forEach items="${dirList }" var="dr">
			<tr>
				<td>${dr.P_NO }</td>
				<td>${dr.DIR_CATEGORY }</td>
				<td><a href="detailDirectorRequest.do?p_no=${dr.P_NO }">${dr.P_TITLE }</a></td>
				<td>${dr.P_REGDATE }</td>
				<td>${dr.DUE_DATE }</td>
				<td>${dr.P_HIT }</td>
				<td>${dr.PROS }</td>
				<td>${dr.CONS }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>