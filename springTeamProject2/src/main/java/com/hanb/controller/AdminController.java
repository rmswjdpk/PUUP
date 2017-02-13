package com.hanb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.MemberDao;
import com.hanb.enums.Board;
import com.hanb.enums.Member;
import com.hanb.service.RService;
import com.hanb.vo.MemberVo;
import com.hanb.vo.SearchVo;

@Controller
public class AdminController {
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private RService rService;
	
	private ModelAndView setViewPage(String viewPageName, ModelAndView mav){
		mav.addObject("adminViewPage", viewPageName);
		mav.addObject("viewPage", "admin/adminFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("adminMain.do")
	public ModelAndView adminMain(HttpSession session) throws IOException{
		String savePath = session.getServletContext().getRealPath("resources/upload/img");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("report1",rService.averageDate(savePath));
		mav.addObject("report2",rService.memberAge(savePath));
		
		mav.addObject("report",rService.getMonthReport(savePath));
		mav.addObject("countMember",dao.getCountMember());
		return setViewPage("adminMain.jsp",mav);
	}
	
	@RequestMapping("memberManagement.do")
	public ModelAndView memberManagement(HttpSession session, SearchVo s){
		ModelAndView mav = new ModelAndView();
		mav.addObject("cntlist", dao.getCountEachGradeMember());
		//회원 목록
		mav.addObject("list", dao.listMember(s));
		//뷰 페이지 세팅
		if(s.getKeyField() != null && s.getKeyField().equals("m.g_num")){
			s.setKeyWord(null);
		}
		session.setAttribute("s", s);
		
		return setViewPage("memberManagement.jsp",mav);
	}
	
	@RequestMapping("detailMember.do")
	public ModelAndView detailMember(HttpSession session, String m_id) throws IOException{
		ModelAndView mav = new ModelAndView();
		MemberVo m = dao.detailMember(m_id);
		if(Member.DIRECTOR.getNumber() == m.getG_num()){
			String savePath = session.getServletContext().getRealPath("resources/upload/img");
			mav.addObject("pieImg", rService.getMemberContractReportGraphFile(m_id, savePath));
		}
		
		mav.addObject("dirCode", Member.DIRECTOR.getNumber());
		mav.addObject("list", dao.listGrade());
		mav.addObject("m", m);
		
		return setViewPage("detailMember.jsp",mav);
	}
	
	@RequestMapping("updateMemberGrade.do")
	public void updateMemberGrade(HttpSession session,HttpServletResponse response,MemberVo m) throws IOException{
		int re = dao.updateMember(m);
		if(re == 1){
			response.getWriter().print(true);
		}
		else{
			response.getWriter().print(false);
		}
	}
	
	@RequestMapping("deleteMember.do")
	public void deleteMember(HttpServletResponse response,String m_id) throws IOException{
		int re = dao.deleteMember(m_id);
		if(re == 1){
			response.getWriter().print(true);
		}
		else{
			response.getWriter().print(false);
		}
	}
	
	@RequestMapping("wordcloud.do")
	public ModelAndView deleteMember(HttpSession session) throws IOException{
		String savePath = session.getServletContext().getRealPath("resources/upload/img");
		ModelAndView mav = new ModelAndView();
		mav.addObject("QandA",rService.wordCloud(savePath,"word.png",Board.QandA));
		mav.addObject("pup",rService.wordCloud(savePath,"word1.png",Board.PUP));
		return setViewPage("wordcloud.jsp", mav);
	}
}