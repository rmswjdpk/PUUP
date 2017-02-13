<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Notice Board here</title>
</head>
<body>
<h2>글 수정하기</h2>
	<hr>
	<form action="updateNotice.do" method="post">
		<table class="table">
			<tr>
				<td colspan="2">제목 : <input type="text" value="${p.p_title }" name="p_title"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="10" cols="100" name="p_content" class="ckeditor">${p.p_content }</textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정" class="btn btn-default"></td>
			</tr>
		</table>
		<input type="hidden" name="p_no" value="${p.p_no }">
	</form>
</body>
</html>