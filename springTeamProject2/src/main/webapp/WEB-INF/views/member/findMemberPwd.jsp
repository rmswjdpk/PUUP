<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	$("#btn-check").click(function() {
		var data = $("form").serialize();
		$.ajax({
			url:"findMemberPwd.do",
			type:"post",
			data:data
		}).done(function(data){
			var r = eval("("+data+")");
			if(r == true){
				alert("회원님의 이메일 계정으로 임시비밀번호가 발급되었습니다. \n 확인 부탁드립니다.");
			}
			else if(r == false){
				alert("아이디나 이메일주소가 올바르지 않습니다.");
			}
		});
	});
});
</script>
</head>
<body>
아이디와 이메일 주소를 입력해 주세요.
<br>
<form action="#" method="post">
<input type="text" name="m_id" class="form-control" placeholder="아이디를 입력해 주세요">
<br>
<input type="email" name="m_email" class="form-control" placeholder="이메일주소를 입력해 주세요">
<br>
<button type="button" id="btn-check" class="btn btn-default">확인</button>
</form>
</body>
</html>