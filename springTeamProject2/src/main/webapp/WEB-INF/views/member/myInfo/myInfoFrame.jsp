<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	var viewPage;
	$("#a-updateInfo,#btn-leave").click(function(){
		$("#div-modal-window").css("display","block");
		if($(this).is($("#a-updateInfo"))){
			viewPage = "updateMyInfo.do";
		}
		else{
			viewPage = "leaveMemberOk.do";
		}
	});
	
	$("#btn-check").click(function(){
		var id = $("#hidden-id").val();
		var pwd = $("#input-checkpwd").val();
		if(pwd.length == 0){
			alert("비밀번호가 입력되지 않았습니다.");
			return;
		}
		getPublicKey().then(function(data){
			var r = eval("("+data+")");
			var modulus = r.publicKeyModulus;
			var exponent = r.publicKeyExponent;
			pwd = encryptValue(modulus,exponent,pwd);
			return pwd;
		}).then(function(pwd){
			checkMember(id,pwd).then(function(data){
				if(data == false){
					alert("비밀번호가 맞지 않습니다.");
				}
				else{
					$("#div-modal-window").css("display","none");
					location.replace(viewPage);
				}
			});
		});
	});
});
</script>
</head>
<body>
<h3>${lm.m_id }님 안녕하세요?!?!?!?</h3>
회원님 등급 : ${lm.g_title }
<table class="table">
<tr>
	<th><a href="myActionList.do">나의 활동 내역</a></th>
	<th><a href="mylistPupRequest.do?m_id=${lm.m_id }">나의 PUP신청 목록보기</a></th>
	<th><a href="myListDirectorRequest.do">나의 디렉터 신청 목록</a></th>
	<th><a href="#" id="a-updateInfo">개인 정보 수정</a></th>
	<th><a href="leaveMember.do">회원 탈퇴</a></th>
</tr>
<tr>
	<td colspan="4">
		<jsp:include page="${myInfoViewPage }"></jsp:include>
	</td>
</tr>
</table>
<!-- 본인 확인 페이지 -->
<div id="div-modal-window">
	<div id="div-modal-content">
		<h2>비밀번호 확인</h2>
		고객님의 개인정보 보호를 위해 비밀번호를 확인합니다
		<hr>
		<input type="hidden" id="hidden-id" value="${lm.m_id }">
		<input type="password" id="input-checkpwd" placeholder="비밀번호를 입력하세요" class="form-control"><br>
		<button type="button" id="btn-check" class="btn btn-default">확인</button>
	</div>
</div>
</body>
</html>