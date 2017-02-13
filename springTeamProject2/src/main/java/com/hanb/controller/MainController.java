package com.hanb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.DirectorRequestDao;
import com.hanb.dao.MemberDao;
import com.hanb.dao.PupRequestDao;
import com.hanb.enums.Member;
import com.hanb.service.FileService;
import com.hanb.vo.SearchVo;

@Controller
public class MainController {
	public static final int DIR_RESTRICT =1;
	
	@Autowired private MemberDao mdao;
	@Autowired private DirectorRequestDao dao;
	@Autowired private PupRequestDao pdao;
	
	@RequestMapping("front.do")
	public ModelAndView front(HttpSession session){
		ModelAndView mav = new ModelAndView();
		SearchVo s = new SearchVo();
		s.setKeyField("g_num");
		s.setKeyWord(Member.DIRECTOR.getNumberAsString());
		
		dao.updateGrade(DIR_RESTRICT);
		dao.updateDate();
		pdao.updateDate();
		
		mav.addObject("cnt",mdao.getCountMember(s));
		mav.addObject("viewPage","front.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("removeOpenModalSession.do")
	@ResponseBody
	public void removeSession(HttpSession session){
		session.removeAttribute("loginModalOpen");
	}
	
	@RequestMapping("error.do")
	public ModelAndView error(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewPage","error.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("crudError.do")
	public ModelAndView crudError(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewPage","crudError.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("unCertificatedError.do")
	public ModelAndView unCertificatedError(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewPage","unCertificatedError.jsp");
		mav.setViewName("main");
		return mav;
	}
}