package com.hanb.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository
public class Rsa {
	private static Rsa rsa = new Rsa();
	private Rsa(){}
	public static Rsa getInstance(){return rsa;}
	
	private final int KEY_SIZE = 1024;
	
	public Map<String, String> generateKey(HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		try{
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(KEY_SIZE);
			
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			session.setAttribute("__privateKey__", privateKey);
			
			RSAPublicKeySpec publicKeySpec = (RSAPublicKeySpec)keyFactory.getKeySpec(publicKey,RSAPublicKeySpec.class);
			String publicModulus = publicKeySpec.getModulus().toString(16);
			String publicExponent = publicKeySpec.getPublicExponent().toString(16);
			
			map.put("publicKeyModulus", publicModulus);
			map.put("publicKeyExponent", publicExponent);
		}catch(NoSuchAlgorithmException e){System.out.println(e);
		}catch(InvalidKeySpecException e){System.out.println(e);}
		return map;
	}
	
	public String decryptRsa(String securedValue, PrivateKey privateKey){
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (Exception e) {System.out.println(e);} 
		
		byte[] encryptedBytes = hexTobyteArray(securedValue);
		
		String decryptedValue = null;
		try{
			cipher.init(Cipher.DECRYPT_MODE,privateKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			decryptedValue = new String(decryptedBytes, "UTF-8");
		} catch (Exception e) {System.out.println(e);} 
		return decryptedValue;
	}
	
	private byte[] hexTobyteArray(String hex){
		byte[] bytes = new byte[hex.length()/2];
		for(int i = 0; i < hex.length(); i+=2){
			byte val = (byte)Integer.parseInt(hex.substring(i, i+2), 16);
			bytes[(int)Math.floor(i/2)] = val;
		}
		return bytes;
	}
}
