<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="./resources/js/member/addrFinder.js"></script>
<script type="text/javascript">
$(function(){
	var pwd2Ok = false;
	$("#input-pwd1").keyup(function(){
		$("#input-pwd2").attr("disabled","disabled");
		var inputVal = $(this).val();
		var size = inputVal.length;
		if(size == 0){
			$("#p-pwd1").html("");
			return;
		}
		if(size < 8){
			$("#p-pwd1").html("최소 8자 이상이여야 합니다.");
			return;
		}

		var ch = 0, num = 0;
		var arr = new Array();
		for(var i = 0; i < size; i++){
			var c = inputVal.charCodeAt(i);
			
			if(i < size - 2){
				var c1 = inputVal.charCodeAt(i+1);
				var c2 = inputVal.charCodeAt(i+2);
				if(c == c1 || c1 == c2 || (c == c1 && c1 == c2)){
					$("#p-pwd1").html("연속적으로 같은 값을 사용할 수 없습니다.");
					return;
				}
			}
			
			if(c >= 48 && c <= 57){
				num++;
			}
			if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){
				ch++;
			}
			if(num == size || ch == size){
				$("#p-pwd1").html("한 종류의 문자로만 구성할 수 없습니다.");
				return;
			}
		};
		
		/* 비밀번호 검사 통과 */
		$("#p-pwd1").html("사용가능한 비밀번호 입니다.");
		$("#input-pwd2").removeAttr("disabled");
		$("#p-pwd2").html("");
	});
	
	/* 비밀번호 확인 검사*/
	$("#input-pwd2").keyup(function(){
		pwd2Ok = false;
		var pwd = $("#input-pwd1").val();
		if($(this).val() == pwd){
			$("#p-pwd2").html("");
			pwd2Ok = true;
		}
		else{
			$("#p-pwd2").html("비밀번호가 서로 다릅니다.");
		}
	});
	
	$("#btn-mail").click(function() {
		var email = $("#input-email").val();
		checkEmail(email).then(function(data){
			if(data == 0){
				$.ajax({
					url:"updateEmail.do",
					type:"post",
					data:{m_email:email},
					success:function(data){
						var r = eval("("+data+")");
						if(r == 1){
							$("#p-email").html("이메일이 해당 주소로 발송되었습니다. 확인 부탁드립니다.");
							$("#input-email").attr("disabled","disabled");
						}
						else if (isNaN(r)){
							$("#p-email").html("회원님 정보 수정에 실패하였습니다. 다시 시도해 주세요.");
						}
						else{
							$("#p-email").html(data);
						}
					}
				})
			}
			else if(data > 0){
				$("#p-email").html("이미 사용중인 이메일 주소 입니다.");
			}
			else{
				$("#p-email").html(data);
			}
		});
	});
	
	$("#btn-update").click(function(){
		if($("#input-postcode").val().length != 0){
			var postcode = $("#input-postcode").val();
			var address = $("#input-address").val();
			var address2 = $("#input-address2").val();
			$("#hidden-addr").val(postcode+"/"+address+"/"+address2);
		}
		var pwd = $("#input-pwd2").val();
		if(pwd.length == 0){
			$("#form-update").submit();
		}
		else{
			if(pwd2Ok == false){
				alert("비밀번호가 양식을 지키지 않았습니다.");
				return;
			}
			else{
				getPublicKey().then(function(data){
					var r = eval("("+data+")");
					var modulus = r.publicKeyModulus;
					var exponent = r.publicKeyExponent;
					pwd = encryptValue(modulus,exponent,pwd);
					$("#securedPwd").val(pwd);
					$("#form-update").submit();
				});
			}
		}
	});
});
</script>
</head>
<body>
<form action="updateMyInfo.do" id="form-update" method="post">
	<table class="table table-bordered">
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="input-pwd1" placeholder="변경하실 경우에만 입력하세요"></td>
		<td width="40%"><p id="p-pwd1"></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" id="input-pwd2" disabled="disabled"></td>
		<td><p id="p-pwd2"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="m_tel" value="${m.m_tel }"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><input type="text" id="input-postcode" value="${addr.postcode }"></td>
		<td><button type="button" id="btn-addrfind" class="btn btn-default">우편번호 찾기</button></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="text" id="input-address" value="${addr.address }" readonly="readonly"></td>
		<td><input type="text" id="input-address2" value="${addr.address2 }"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="email" name="m_email" id="input-email" <c:if test="${lm.g_num != 10 }">disabled="disabled"</c:if> value="${m.m_email }"></td>
		<td>
		<c:choose>
		<c:when test="${lm.g_num == 10 }">
			<button id="btn-mail" type="button" class="btn btn-default">인증 메일 받기</button>
		</c:when>
		<c:otherwise>이미 인증된 회원입니다.</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<p id="p-email">
		<c:if test="${lm.g_num == 10 }">
		회원님은 아직 인증되지 않으셨으므로 등록하신 이메일을 확인하신 후,
		<br /> 
		주소가 잘못되었으면 다시 작성하여 메일 받기를 클릭하시거나, 주소가 틀리지 않았다면 다시 메일발송을 눌러 주시길 바랍니다.
		</c:if>
		</p>
		</td>
		<td></td>
	</tr>
	</table>
	<input type="hidden" name="m_id" value="${m.m_id }" readonly="readonly">
	<input type="hidden" name="m_name" value="${m.m_name }" readonly="readonly">
	<input type="hidden" name= "m_addr" id="hidden-addr">
	<input type="hidden" name= "m_pwd" id="securedPwd">
	<button id="btn-update" type="button" class="btn btn-default">수정</button>
</form>
</body>
</html>