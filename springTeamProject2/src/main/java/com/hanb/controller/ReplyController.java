package com.hanb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanb.service.ReplyService;
import com.hanb.vo.ReplyVo;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService service;

	@RequestMapping(value="listReply.do",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getReplys(int p_no) throws JsonProcessingException{
		return service.getJSONReplys(p_no);
	}
	
	@RequestMapping(value="insertReply.do",method=RequestMethod.POST)
	@ResponseBody
	public void insertReplys(HttpServletResponse res,ReplyVo r) throws IOException {
		boolean re = service.insertReply(r);
		res.getWriter().print(re);
	}
	
	@RequestMapping(value="deleteReply.do")
	@ResponseBody
	public void deleteReply(HttpServletResponse res,int r_no) throws IOException {
		boolean re = service.deleteReply(r_no);
		res.getWriter().print(re);
	}
}
