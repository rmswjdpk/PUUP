package com.hanb.controller;

import java.io.IOException;
import java.security.PrivateKey;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.MemberDao;
import com.hanb.service.MemberService;
import com.hanb.vo.LoginMemberVo;
import com.hanb.vo.MemberVo;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private MemberDao dao;
	
	private ModelAndView setViewPage(String viewPageName, ModelAndView mav){
		mav.addObject("viewPage", "member/"+viewPageName);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="insertMember.do",method=RequestMethod.GET)
	public ModelAndView insertForm(HttpSession session){
		ModelAndView mav = new ModelAndView();
		return setViewPage("insertMember.jsp",mav);
	}
	
	@RequestMapping(value="insertMember.do",method=RequestMethod.POST)
	public ModelAndView insertSubmit(HttpSession session, MemberVo m){
		ModelAndView mav = new ModelAndView();
		
		boolean insertResult = service.insertMember(m, (PrivateKey)session.getAttribute("__privateKey__"));
		session.removeAttribute("__privateKey__");
		
		if(insertResult == true){
			mav.addObject("msg", m.getM_name()+"�� ������ ���ϵ帳�ϴ�. <br> ȸ�������� ������ �ϱ� ���Ͽ�, ���Ϸ� ���۵� ��ũ�� Ŭ���Ͽ� �ֽñ� �ٶ��ϴ�.");
		}
		else{
			mav.addObject("msg", "���� ����. �ٽ� �õ��� �ּ���");
		}
		return setViewPage("insertMemberOk.jsp",mav);
	}
	
	@RequestMapping("checkValue.do")
	@ResponseBody
	public void checkValue(HttpSession session,HttpServletResponse response, String value) throws IOException{
		//�α����� ȸ���� �̸��� ������ �� ��� ȸ�������Ҷ� ���� �ּҶ� �Ȱ��� ������ ��û�� ��� 0�� ��ȯ�Ѵ�
		LoginMemberVo lm = (LoginMemberVo)session.getAttribute("lm");
		if(lm != null && lm.getG_num() != 0){
			MemberVo m = dao.detailMember(lm.getM_id());
			if(m.getM_email().equals(value.split("=")[1])){
				response.getWriter().print(0);
				return;
			}
		}
		int b = dao.checkValue(value);
		response.getWriter().print(b);
	}
	
	@RequestMapping(value="checkMember.do",method=RequestMethod.POST)
	@ResponseBody
	public void checkMember(HttpSession session, HttpServletResponse response, String m_id, String m_pwd) throws IOException {
		PrivateKey privateKey = (PrivateKey)session.getAttribute("__privateKey__");
		session.removeAttribute("__privateKey__");
		
		boolean loginResult = service.checkMember(m_id, m_pwd, privateKey);
		if(loginResult == true){
			session.setAttribute("lm", service.login(m_id));
			String url = (String)session.getAttribute("loginAfterRequest");
			if(url == null)
				response.getWriter().println(loginResult);
			else
				response.getWriter().println(url);
		}
		else{
			response.getWriter().println(loginResult);
		}
	}
	
	@RequestMapping("logoutMember.do")
	public ModelAndView logoutMember(HttpSession session){
		ModelAndView mav = new ModelAndView();
		LoginMemberVo m = (LoginMemberVo)session.getAttribute("lm");
		if(m != null){
			session.invalidate();
		}
		mav.addObject("viewPage", "front.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="findMemberId.do",method=RequestMethod.GET)
	public ModelAndView findMemberIdForm(){
		ModelAndView mav = new ModelAndView();
		return setViewPage("findMemberId.jsp",mav);
	}
	
	@RequestMapping(value="findMemberId.do",method=RequestMethod.POST)
	@ResponseBody
	public void findMemberId(HttpServletResponse response,String m_email) throws IOException{
		String id = service.getMemberId(m_email);
		response.getWriter().print(id);
	}
	
	@RequestMapping(value="findMemberPwd.do",method=RequestMethod.GET)
	public ModelAndView findMemberPwdForm(){
		ModelAndView mav = new ModelAndView();
		return setViewPage("findMemberPwd.jsp",mav);
	}
	@RequestMapping(value="findMemberPwd.do",method=RequestMethod.POST)
	@ResponseBody
	public void findMemberPwd(HttpServletResponse response,String m_id, String m_email) throws IOException{
		boolean result = service.generateTempPwdAndSendEmail(m_id, m_email);
		response.getWriter().print(result);
	}
	
	@RequestMapping(value="upgradeMember.do",method=RequestMethod.GET)
	public ModelAndView upgradeMember(HttpSession session,String m_id){
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", service.upgradeMember(m_id));
		return setViewPage("upgradeMember.jsp",mav);
	}
}