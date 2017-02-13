<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail NoticeBoard here</title>
</head>
<body>
	<h2>공지사항</h2>
	<hr>
	<input type="hidden" id="p_no" value="${de.p_no }">
	<table id="tb" class="table">
		<tr>
			<td colspan="2">제목:${de.p_title }</td>
			<td colspan="1" align="left">게시일:${de.p_regdate }</td>
		</tr>

		<tr>
			<td colspan="3">
			<div class="form-control" style="min-height: 300px">${de.p_content}</div>
			</td>
		</tr>

		<tr>
			<td><a href="listNotice.do">목록으로</a></td>
			<c:if test="${lm.g_num == 40}">
			<td>
			<a href="updateNotice.do?p_no=${de.p_no }">수정</a> 
			<a href="deleteNotice.do?p_no=${de.p_no }">삭제</a>
			</td>
			</c:if>
		</tr>
	</table>
</body>

</html>