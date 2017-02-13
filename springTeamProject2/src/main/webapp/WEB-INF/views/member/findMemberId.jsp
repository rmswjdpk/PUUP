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
		var email = $("#input-email").val();
		checkEmail(email).then(function(data) {
			$("body").find("p").remove();
			if(data == 0 || isNaN(data)){
				$("<p></p>").html("이메일주소가 존재하지 않거나 올바르지 않습니다.").appendTo("body");
			}
			else{
				$.ajax({
					url:"findMemberId.do",
					type:"post",
					data:{m_email:email},
					success:function(data){
						//var r = eval("("+data+")");
						$("<p></p>").html("회원님의 아이디는 "+data.replace(/..$/,"**")+" (보안을 위하여 뒤쪽 2자리는 표시 안합니다.) 입니다.").appendTo("body");
					}
				});
			}
		});
	});
});
</script>
</head>
<body>
회원 가입시 입력하셨던 이메일 주소를 입력해 주세요.
<br>
<input type="email" id="input-email" class="form-control" placeholder="이메일주소를 입력해 주세요">
<br>
<button type="button" id="btn-check" class="btn btn-default">확인</button>
</body>
</html>