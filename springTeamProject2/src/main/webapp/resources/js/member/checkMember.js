/**
 * 
 */
//특정 값 존재 여부 확인
// 존재하지 않으면 0 존재하면 그 숫자만큼 반환
function checkValue(checkValue) {
	return new Promise(function(resolve, reject) { 
		$.ajax({
			url:"checkValue.do?",
			data:{value:checkValue},
			success:function(data){
				var r = eval("("+data+")");
				return resolve(r);
			}
		});
	});
}
//아이디와 비밀번호를 전송
function checkMember(id,pwd){
	return new Promise(function(resolve, reject) {
		$.ajax({
			url:"checkMember.do",
			type:"post",
			data:{m_id:id,m_pwd:pwd},
			success:function(data){
				try{
					var r = eval("("+data+")");
					return resolve(r);
				}catch(err){
					return resolve(data);
				}
			}
		});
	});
}

//파라메터로 이메일값이 넘어오면 정규식으로 검사한 뒤 올바른 이메일 주소면 checkValue 통해 Promise를 반환
function checkEmail(email){
	var emailCheck = /^([\w\.\-_]+)?\w+@[\w-_]+(\.\w+){1,}$/;
	var r = emailCheck.test(email);
	if(r == true){
		email = "m_email="+email;
		return checkValue(email);
	}
	else{
		return Promise.resolve("올바르지 않는 이메일 주소 입니다.");
	}
}
