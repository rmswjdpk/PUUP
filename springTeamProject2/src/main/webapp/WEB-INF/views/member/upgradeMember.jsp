<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	var r = eval($("#result").val());
	if(r == 1){
		alert("인증되셨습니다. \n 메인페이지로 이동합니다.");
	}
	else if(r == 0){
		alert("인증에 실패하셨습니다. 관리자에게 문의해 주세요. \n 메인페이지로 이동합니다.");
	}
	else{
		alert("이미 인증된 회원입니다. \n 메인페이지로 이동합니다.");
	}
	location.replace("front.do");
});
</script>
</head>
<body>
<input type="hidden" id="result" value="${result }">
</body>
</html>