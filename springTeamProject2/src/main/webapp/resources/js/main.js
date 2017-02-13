/**
 * 
 */
$(function(){
	if($("#modalOpen").val() == "true"){
		$.ajax({url:"removeOpenModalSession.do"}).done(function(){
			$("#div-modal-window").css("display","block");
		});
	}
	
	$("#a-login").click(function(){
		$("#div-modal-window").css("display","block");
	});
	
	$("#div-modal-window").click(function(e){
		if($(e.target).is($(this))){
			$("#div-modal-window").css("display","none");
		}
	});
	
	$("#btn-login").click(function(){
		var id = $("#input-loginid").val();
		var pwd = $("#input-loginpwd").val();
		if(id.length == 0 || pwd.length == 0){
			alert("아이디나 비밀번호가 입력되지 않았습니다.");
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
				if(data == true){
					$("#div-modal-window").css("display","none");
					location.replace("front.do");
				}
				else if(data == false){
					alert("아이디나 비밀번호가 올바르지 않습니다.");
				}
				else{
					location.replace(data);
				}
			});
		});
	});
});