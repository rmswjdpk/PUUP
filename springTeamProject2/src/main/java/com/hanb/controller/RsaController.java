package com.hanb.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanb.security.Rsa;

@Controller
public class RsaController {
	@Autowired
	private Rsa rsa;
	
	@RequestMapping("getPublicKey.do")
	@ResponseBody
	public void getPublicKey(HttpSession session,HttpServletResponse response) throws IOException{
		Map<String, String> map = rsa.generateKey(session);
		session.setMaxInactiveInterval(60*3);
		String responseText = "";
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			responseText += "'"+key+"':'"+map.get(key)+"',";
		}
		responseText = "{"+responseText.substring(0, responseText.lastIndexOf(","))+"}";
		response.getWriter().print(responseText);
	}
}
