/**
 * 
 */

/* 암호화 한다 */
function encryptValue (modulus,exponent,value) {
	var rsa = new RSAKey();
	rsa.setPublic(modulus,exponent);
	var securedValue = rsa.encrypt(value);
	return securedValue;
};

/* 공개키를 불러온다*/
function getPublicKey (){
	return  $.ajax({
		url:"getPublicKey.do"
	});
};