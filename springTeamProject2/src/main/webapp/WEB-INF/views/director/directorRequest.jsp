<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="./resources/js/uploadFile.js"></script>
<script type="text/javascript">
$(function() {
	$("#insertSubmit").click(function(){
		if(fileUpload != 3){
			alert("무조건 파일을 3개 올리셔야 합니다.")
			return;
		}
		$("form").submit();
	});
});
</script>
</head>
<body>
	<h2>디렉터 신청</h2>
	<hr>
	<form action="directorRequest.do" method="post" >
	<input type="hidden" name="p_title" value="${lm.m_id }회원님의 디렉터신청 입니다." readonly="readonly">
	<input type="hidden" readonly="readonly" value="${lm.m_id }"  name="m_id">
	<textarea class="ckeditor" rows="10" cols="80" name="p_content" required="required"></textarea><br><br>
	<input type="file" id="input-file" style="display: none;">
	<br>
	<button id="btn-uploadFile" type="button" value="3">파일첨부</button>
	<div id="div-uploadFiles"></div>
	<input type="button" value="디렉터 신청" id="insertSubmit">
	<font color="red">**파일은 3개를 올려주세요!!</font> 
	</form>
</body>
</html>