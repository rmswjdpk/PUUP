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
<h2>PUP 신청 목록</h2>
<hr>
	<table class="table table-bordered table-hover">
		<tr>
			<th>글번호</th>
			<th>분류</th>
			<th>제목</th>
			<th>아이디</th>
			<th>샘플기한</th>
			<th>제작기한</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${puList }" var="pu">
			<tr>
				<td>${pu.p_no }</td>
				<td>${pu.pu_category }</td>
				<td>
					<a href="detailPupRequest.do?p_no=${pu.p_no }">${pu.p_title }</a>
				</td>
				<td>${pu.m_id }</td>
				<td>${pu.sample_period }</td>
				<td>${pu.product_period }</td>
				<td>${pu.p_regdate }</td>
				<td>${pu.p_hit }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>