<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link  href="./resources/datepicker/datepicker.css" rel="stylesheet">
<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="./resources/datepicker/datepicker.js"></script>
<script type="text/javascript" src="./resources/js/uploadFile.js"></script>
<script type="text/javascript">
	$(function () {
		$("#insertSubmit").click(function(){
			if(fileUpload != 1){
				alert("무조건 파일을 1개 올리셔야 합니다.")
				return;
			}
			$("form").submit();
		});
		
		$('#sam_per,#pro_per').datepicker({
            format: 'yy/mm/dd',
            numberOfMonths: 1,
            autoHide:true,
            startDate:function(){
    			return new Date();
    		}
        });
	})
</script>
</head>
<body>
<h2>PUP 신청</h2>
<hr>
	<form action="insertPupRequest.do" method="post">
		제목 : <input type="text" name="p_title">
		<input type="hidden" name="m_id" value="${lm.m_id }" readonly="readonly"><br>
		<textarea class="ckeditor" rows="10" cols="120" name="p_content"></textarea><br>
		샘플 기한 : <input type="text" name="sample_period" id="sam_per"><br>
		마감 기한 : <input type="text" name="product_period" id="pro_per"><br>
		희망 가격대 : <input type="number" name="expection_price"><br>
		<input type="file" id="input-file" style="display: none;">
		<br>
		<button id="btn-uploadFile" type="button" value="1" class="btn btn-default">파일첨부</button>
		<div id="div-uploadFiles"></div>
		<input type="button" value="PUP 신청" class="btn btn-default" id="insertSubmit">
	</form>
</body>
</html>