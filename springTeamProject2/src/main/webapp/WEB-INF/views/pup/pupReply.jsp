<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/js/uploadFile.js"></script>
<script type="text/javascript">
$(function() {
	$("#insertSubmit").click(function(){
		if(fileUpload != 1){
			alert("무조건 파일을 1개 올리셔야 합니다.")
			return;
		}
		$("form").submit();
	});
})
</script>
</head>
<body>
	<form action="pupReply.do" method="post">
		<input type="hidden" name="p_no" value="${p_no }">
		아이디 : <input type="text" name="m_id" value="${lm.m_id }"><br>
		내용 : <br>
		<textarea rows="10" cols="80" name="r_content" placeholder="이곳에 내용을 입력하세요"></textarea><br>
		<input type="file" id="input-file" style="display: none;">
		<br>
		<button id="btn-uploadFile" type="button" value="1" class="btn btn-default">파일첨부</button>
		<div id="div-uploadFiles"></div>
		<input type="button" value="PU 해주기" class="btn btn-default" id="insertSubmit">
	</form>
</body>
</html>