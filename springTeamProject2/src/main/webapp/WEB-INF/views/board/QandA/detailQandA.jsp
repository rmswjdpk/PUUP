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
	<table id="tb" class="table">
		<tr>
			<td>글 번호 :${p.p_no }</td>
			<td>작성일 : ${p.p_regdate }</td>
		</tr>
		<tr>
			<td colspan="2">
			<h2>${p.p_title }</h2>
			<hr>
			<div class="form-control" style="min-height: 500px; height: auto;">${p.p_content}</div>
			</td>
		</tr>
		<c:forEach var="f" items="${files }">
		<tr>
		<td>업로드 파일 : <a href="downloadFile.do?f_name=${f.f_name }">${f.f_realname }</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td><a href="listQandA.do">목록으로</a>
			<c:if test="${lm.g_num == 40}">
			<a href="insertQandA.do?p_ref=${p.p_no }">답변하기</a>
			</c:if>
			</td>
			<c:if test="${lm.m_id == p.m_id || lm.g_num == 40}">
			<td>
			<a href="updateQandA.do?p_no=${p.p_no }">수정</a> 
			<a href="deleteQandA.do?p_no=${p.p_no }">삭제</a>
			</td>
			</c:if>
		</tr>
	</table>
</body>

</html>