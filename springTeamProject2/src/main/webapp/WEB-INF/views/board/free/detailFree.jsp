<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail NoticeBoard here</title>
<script type="text/javascript" src="./resources/js/post/reply.js"></script>
<link rel="stylesheet" href="./resources/css/reply.css">
</head>
<body>
	<table id="tb" class="table">
		<tr>
			<td>글 번호 :${p.p_no }</td>
			<td>작성일 : ${p.p_regdate }</td>
			<td>조회수 : ${p.p_hit }</td>
		</tr>
		<tr>
			<td colspan="3">
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
			<td><a href="listFree.do">목록으로</a></td>
			<c:if test="${lm.m_id == p.m_id}">
			<td>
			<a href="updateFree.do?p_no=${p.p_no }">수정</a> 
			<a href="deleteFree.do?p_no=${p.p_no }">삭제</a>
			</td>
			</c:if>
		</tr>
	</table>
	<!-- 리플 시스템 -->
	<!-- 작성된 리플들은 보여주는 리플 컨테이너 -->
	<div id="div-replyContainer" class="table">
		<c:forEach items="${rlist }" var="r">
			<div id="${r.r_no }" class="div-reply">
				<p>글쓴이 : ${r.m_id } <span class="regdate">(${r.r_regdate })</span></p>
				<div>${r.r_content }</div>
				<c:if test="${lm.m_id.equals(r.m_id) }">
				<button type="button" class="btn_delete" value="${r.r_no }">삭제</button>
				</c:if>
				<hr>
			</div>
		</c:forEach>
		<br>
		<!-- 리플 작성하는 Form -->
		<form action="insertReply.do" method="post" id="form_reply">
			<input type="hidden" id="m_id" name="m_id" value="${lm.m_id }">
			<input type="hidden" id="p_no" name="p_no" value="${p.p_no }">
			<textarea rows="5" cols="60" name="r_content"></textarea>
			<input type="button" value="등록" id="btn_insert" style="display: inline-block;">
		</form>
	</div>
	<!-- 리플 시스템 끝 -->
</body>
</html>