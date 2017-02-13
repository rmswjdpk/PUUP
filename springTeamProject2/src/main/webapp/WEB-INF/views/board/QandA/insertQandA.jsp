<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="./resources/js/uploadFile.js"></script>
<title>Insert NoiceBoard here</title>
</head>

<body>
	<h2>문의게시판</h2>
	<hr>
	<form action="insertQandA.do" method="post">
	<input type="hidden" name="m_id" value="${lm.m_id }">
	<c:if test="${p_ref != null }"><input type="hidden" name="p_ref" value="${p_ref }"></c:if>
		<table class="table">
		<tr>
			<td>제 목 : <input type="text" name="p_title"></td>
		</tr>
		<tr>
			<td><textarea rows="15" cols="100" id="p_content" name="p_content" class="ckeditor"></textarea></td>
		</tr>
		<tr>
		<td>
			<input type="file" id="input-file" style="display: none;">
			<br>
			<button id="btn-uploadFile" type="button" value="3">파일첨부</button>
			<button id="btn-uploadImgFile" type="button" value="3">이미지 파일첨부</button>
			<div id="div-uploadFiles"></div>
		</td>
		</tr>
		<tr>
			<td>
			<input type="submit" value="등록" class="btn btn-default">&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="listQandA.do" class="btn btn-default">목록으로</a>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>