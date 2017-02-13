<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript" src="./resources/js/member/addrFinder.js"></script>
	<script type="text/javascript" src="./resources/js/member/checkMember.js"></script>
	<script type="text/javascript" src="./resources/js/member/insertMember.js"></script>
</head>
<body>
<table>
<tr>
	<th align="center">홈피 이용약관</th>
	<th align="center">개인정보 수집 및 이용 방침</th>
</tr>
<tr>
	<td>
		<div class="form-control" style="min-height: 300px; min-width: 500px; max-width: 500px; max-height: 500px;">
		그런거 없으니까 걍 써
		</div>
		<!-- <textarea rows="20" cols="60" readonly="readonly">그런거 없으니까 걍 써
		</textarea> -->
	</td>
	<td>
		<div class="form-control" style="min-height: 300px; min-width: 500px; max-width: 500px; max-height: 500px;">
		님들 비밀번호는 강력하게 암호화되어서 관리자도 모름. 
		<br>
		진짜 쓰던 비번 써도됨
		</div>
		<!-- <textarea rows="20" cols="60" readonly="readonly">님들 비밀번호는 강력하게 암호화되어서 관리자도 모름. 진짜 쓰던 비번 써도됨
		</textarea> -->
	</td>
</tr>
<tr>
	<th><label><input type="checkbox" id="terms1">홈피 이용약관에 동의합니다.</label></th>
	<th><label><input type="checkbox" id="terms2">개인정보 수집 및 이용 방침에 동의합니다.</label></th>
</tr>
</table>
<div id="div-join" style="display: none;">
	<form action="insertMember.do" id="form-join" method="post">
		<table class="table table-bordered table-hover">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="m_id" id="input-id" placeholder="필수 입력"></td>
			<td width="40%"><p id="p-id"></p></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="m_name" id="input-name" placeholder="필수 입력"></td>
			<td><p id="p-name"></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="m_birth" id="input-birth" placeholder="필수 입력"></td>
			<td>주민등록번호 앞 6자리를 입력해 주세요</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="input-pwd1" placeholder="필수 입력"></td>
			<td><p id="p-pwd1"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" id="input-pwd2" disabled="disabled" placeholder="필수 입력"></td>
			<td><p id="p-pwd2"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="tel" name="m_tel" id="input-tel"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" id="input-postcode"></td>
			<td><button type="button" id="btn-addrfind" class="btn btn-default">우편번호 찾기</button></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" id="input-address" readonly="readonly"></td>
			<td><input type="text" id="input-address2"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="email" name="m_email" id="input-email" placeholder="필수 입력"></td>
			<td><button type="button" id="btn-emailCheck" class="btn btn-default">이메일 확인</button></td>
		</tr>
		<tr>
			<td></td>
			<td><p id="p-email"></td>
			<td></td>
		</tr>
		</table>
		<input type="hidden" name= "m_addr" id="hidden-addr">
		<input type="hidden" name= "m_pwd" id="securedPwd">
		<button id="btn-join" type="button" class="btn btn-default">회원가입</button>
	</form>
</div>
</body>
</html>