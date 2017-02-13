<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<title>Insert NoiceBoard here</title>
</head>

<body>
	<h1>공지사항</h1>
	<hr>
	<form action="insertNotice.do" method="post">
	<input type="hidden" value="${lm.m_id }" name="m_id">
		<table class="table">
		<tr>
			<td>제 목 : <input type="text" name="p_title"></td>
		</tr>
		<tr>
			<td><textarea rows="15" cols="100" name="p_content" class="ckeditor"></textarea></td>
		</tr>
		<tr>
			<td>
			<input type="submit" value="공지등록" class="btn btn-default">&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="listNotice.do" class="btn btn-default">목록으로</a>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>