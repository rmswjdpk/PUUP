<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="./resources/js/uploadFile.js"></script>
<script type="text/javascript" src="./resources/js/post/updatePost.js"></script>
<title>Insert NoiceBoard here</title>
</head>

<body>
	<h2>자유게시판</h2>
	<hr>
	<form action="updateQandA.do" method="post">
	<input type="hidden" value="${lm.m_id }" name="m_id">
	<input type="hidden" value="${p.p_no }" name="p_no">
		<table class="table">
		<tr>
			<td>제 목 : <input type="text" name="p_title" value="${p.p_title }"></td>
		</tr>
		<tr>
			<td><textarea rows="15" cols="100" id="p_content" name="p_content" class="ckeditor">${p.p_content }</textarea></td>
		</tr>
		<tr>
		<td>
			<input type="file" id="input-file" style="display: none;">
			<br>
			<button id="btn-uploadImgFile" type="button" value="3" class="btn btn-default" >파일첨부</button>
			<div id="div-uploadFiles"></div>
		</td>
		</tr>
		<c:forEach var="f" items="${files }">
		<tr id="${f.f_name }" class="tr-uploadFiles">
		<td>업로드 파일 : ${f.f_realname }</td>
		<td><button type="button" value="${f.f_name }" class="btn_delete btn btn-default">삭제</button></td>
		</tr>
		</c:forEach>
		<tr>
			<td>
			<input type="submit" value="수정" class="btn btn-default">&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="listQandA.do" class="btn btn-default">목록으로</a>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>