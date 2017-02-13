<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>관리자 아이디 : ${lm.m_id }</h2>
<table class="table">
<tr>
	<th><a href="adminMain.do">메인</a></th>
	<th><a href="memberManagement.do">회원 관리</a></th>
	<th><a href="wordcloud.do">게시판별 자주 검색된 키워드</a></th>
	<th>게시글 관리</th>
</tr>
<tr>
	<td colspan="3">
		<jsp:include page="${adminViewPage }"></jsp:include>
	</td>
</tr>
</table>
</body>
</html>