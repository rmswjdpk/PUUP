/**
 * 
 */

$(function() {
	/* 약관 둘다 동의하면 회원가입폼 보이게 하는 코드*/
	$("input[type='checkbox']").click(function(){
		if($("#terms1").is(":checked") && $("#terms2").is(":checked")){
			$("#div-join").css("display","block");
		}
		else{
			$("#div-join").css("display","none");
		}
	});
	/* --------------------끝-------------------- */
	
	var idOk = false, nameOk = false, pwd2Ok = false, emailOk = false;
	/* 아이디 검사 */
	$("#input-id").keyup(function(){
		idOk = false;
		var id = $(this).val();
		if(id.length == 0){
			$("#p-id").html("");
			return;
		}
		
		for(var i = 0; i < id.length; i++){
			var c = id.charCodeAt(i);
			if(c < 48){
				$("#p-id").html("알파벳 소문자와 숫자만 가능합니다.");
				return;
			}
			else if(c > 57 && c < 97){
				$("#p-id").html("알파벳 소문자와 숫자만 가능합니다.");
				return;
			}
			else if(c > 122){
				$("#p-id").html("알파벳 소문자와 숫자만 가능합니다.");
				return;
			}
		}
		if(id.length < 4){
			$("#p-id").html("최소 4자 이상이여야 합니다.");
			return;
		}
		if(id.length > 12){
			$("#p-id").html("너무 깁니다!!!");
			return;
		}
		id = "m_id="+id;
		
		checkValue(id).then(function(data){
			if(data == 0){
				$("#p-id").html("사용가능한 아이디 입니다.");
				idOk = true;
			}
			else{
				$("#p-id").html("이미 사용중인 아이디 입니다.");
			}
		});
	});
	/* --------------------끝-------------------- */
	
	/* 이름 검사 */
	$("#input-name").keyup(function(){
		nameOk = false;
		var inputVal = $(this).val();
		var check = /[a-z|A-Z|가-힣]/;

		if(inputVal.length == 0){
			$("#p-name").html("");
			return;
		}
		
		for(var i = 0; i < inputVal.length; i++){
			var c = inputVal.charAt(i);
			if(check.test(c)){
				$("#p-name").html("");
				nameOk = true;
			}
			else{
				$("#p-name").html("부적절한 이름입니다.");
				return;
			}
		}
	});
	
	/* 비밀번호 검사 */

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
			$("#p-pwd2").html("확인되셨습니다");
			pwd2Ok = true;
		}
		else{
			$("#p-pwd2").html("비밀번호가 서로 다릅니다.");
		}
	});
	
	/* 이메일 유효성 검사 */
	$("#btn-emailCheck").click(function(){
		emailOk = false;
		var email = $("#input-email").val();
		checkEmail(email).then(function(data){
			if(data == 0){
				emailOk = true;
				$("#p-email").html("사용 가능한 이메일 주소 입니다.");
			}
			else if (data > 0){
				$("#p-email").html("이미 사용중인 이메일 주소 입니다.");
			}
			else{
				$("#p-email").html(data);
			}
		});
	});
	
	/* 회원가입 버튼 수행 */ 
	$("#btn-join").click(function(){
		if(idOk==false){
			alert("아이디가 양식을 지키지 않았습니다.");
			return;
		}
		if(nameOk==false){
			alert("이름이 양식을 지키지 않았습니다.");
			return;
		}
		if(pwd2Ok==false){
			alert("비밀번호 확인이 양식을 지키지 않았습니다.");
			return;
		}
		if(emailOk == false){
			alert("이메일이 양식을 지키지 않았습니다.");
			return;
		}
		if($("#input-postcode").val().length != 0){
			var postcode = $("#input-postcode").val();
			var address = $("#input-address").val();
			var address2 = $("#input-address2").val();
			$("#hidden-addr").val(postcode+"/"+address+"/"+address2);
		}
		var pwd = $("#input-pwd2").val();
		
		getPublicKey().then(function(data){
			var r = eval("("+data+")");
			var modulus = r.publicKeyModulus;
			var exponent = r.publicKeyExponent;
			pwd = encryptValue(modulus,exponent,pwd);
			$("#securedPwd").val(pwd);
			$("#form-join").submit();
		});
	});
});