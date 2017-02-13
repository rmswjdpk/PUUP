package com.hanb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanb.dao.PupReplyDao;
import com.hanb.vo.PupReplyVo;

@Controller
public class PupReplyController {

	@Autowired
	private PupReplyDao dao;

	public void setDao(PupReplyDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="updateChoice.do")
	@ResponseBody
	public void updateChoice(int p_no, int r_no)
	{
		dao.updateChioce(p_no,r_no);
	}
	
	
	@RequestMapping(value="listPupReply.do", produces="text/plain;charset=utf-8" )
	@ResponseBody
	public String listReply(int p_no)
	{
		String str = "";
		ObjectMapper mapper= new ObjectMapper();
		try{
			str = mapper.writeValueAsString(dao.list(p_no));
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return str;
	}
	
	@RequestMapping(value="pupReply.do", method=RequestMethod.GET)
	public ModelAndView insert_form(int p_no)
	{
		ModelAndView view = new ModelAndView();
		view.addObject("p_no", p_no);
		view.addObject("viewPage", "pup/pupReply.jsp");
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping(value="pupReply.do", method=RequestMethod.POST)
	public ModelAndView insert_form(PupReplyVo prv)
	{
		ModelAndView view = new ModelAndView();
		dao.insert(prv);
		view.setViewName("redirect://detailPupRequest.do?p_no="+prv.getP_no());
		return view;
	}
	
	@RequestMapping(value="pupProductReply.do", method=RequestMethod.GET)
	public ModelAndView productinsert_form(int p_no)
	{
		ModelAndView view = new ModelAndView();
		view.addObject("p_no", p_no);
		view.addObject("viewPage", "pup/pupProductReply.jsp");
		view.setViewName("main");
		return view;
	}
	
	
	@RequestMapping(value="pupProductReply.do", method=RequestMethod.POST)
	public ModelAndView productinsert_form(PupReplyVo prv)
	{
		ModelAndView view = new ModelAndView();
		dao.productInsert(prv);
		view.setViewName("redirect://detailPupRequest.do?p_no="+prv.getP_no());
		return view;
	}
}
