package com.hanb.controller;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.MemberDao;
import com.hanb.dao.PostDao;
import com.hanb.security.BCrypt;
import com.hanb.security.Rsa;
import com.hanb.service.MailService;
import com.hanb.vo.LoginMemberVo;
import com.hanb.vo.MemberVo;

@Controller
public class MyInfoController {

	@Autowired
	private MemberDao dao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private Rsa rsa;
	@Autowired
	private MailService sender;
	
	//개인정보 메인페이지
	//내 활동목록으로 리다이렉트 된다.
	@RequestMapping("myInfo.do")
	public ModelAndView myInfo(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/myActionList.do");
		return mav;
	}
	
	//나의 활동 목록으로 간다. 아직 공사중!!!!!!!!!!!!!!!!!!!
	@RequestMapping("myActionList.do")
	public ModelAndView myActionList(HttpSession session){
		LoginMemberVo lm = (LoginMemberVo)session.getAttribute("lm");
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",pdao.getMyActionList(lm.getM_id()));
		mav.addObject("myInfoViewPage", "myActionList.jsp");
		mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//하나로 합쳐져있는 주소를 우편번호/주소/상세주소로 분할하여 mav에 넣는다.
	private Map<String, String> setAddr(MemberVo m){
		String[] addr = m.getM_addr().split("/");
		Map<String, String> map = new HashMap<String, String>();
		map.put("postcode", addr[0]);
		map.put("address", addr[1]);
		if(addr[2]==null){
			addr[2] = "";
		}
		map.put("address2", addr[2]);
		return map;
	}
	
	//개인정보 수정 페이지로 간다
	@RequestMapping(value="updateMyInfo.do",method=RequestMethod.GET)
	public ModelAndView updateForm(HttpSession session){
		ModelAndView mav = new ModelAndView();
		LoginMemberVo lm = (LoginMemberVo) session.getAttribute("lm");
		MemberVo m = dao.detailMember(lm.getM_id());
		if(m.getM_addr() != null && m.getM_addr().length() != 0){
			mav.addObject("addr", setAddr(m));
		}
		mav.addObject("m", m);
		mav.addObject("myInfoViewPage", "updateMyInfo.jsp");
		mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//개인정보 수정내용을 업데이트 한다
	@RequestMapping(value="updateMyInfo.do",method=RequestMethod.POST)
	public ModelAndView updateSubmit(HttpSession session,MemberVo m){
		ModelAndView mav = new ModelAndView();
		if(m.getM_pwd().length() > 1){
			PrivateKey privateKey = (PrivateKey)session.getAttribute("__privateKey__");
			m.setM_pwd(BCrypt.hashpw(rsa.decryptRsa(m.getM_pwd(), privateKey), BCrypt.gensalt(12)));
		}
		int re = dao.updateMember(m);
		if(re == 1){
			mav.addObject("msg", "수정 성공");
		}
		else{
			mav.addObject("msg", "수정 실패");
		}
		mav.addObject("myInfoViewPage", "resultPage.jsp");
		mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//
	@RequestMapping(value="updateEmail.do",method=RequestMethod.POST)
	@ResponseBody
	public void updateEmail(HttpServletResponse response,HttpSession session,String m_email) throws IOException{
		LoginMemberVo lm = (LoginMemberVo)session.getAttribute("lm");
		MemberVo m = dao.detailMember(lm.getM_id());
		m.setM_email(m_email);
		int re = dao.updateMember(m);
		if(re == 1){
			String title = "PUP 회원님 이메일 재인증 안내";
			String content = "<h2>안녕하십니까? "+m.getM_name()+"님</h2><br /> "
					+ "저희 사이트는 이메일을 통한 회원 인증이 되지 않으면 원활한 이용이 불가능 합니다. 그렇기 때문에 "+m.getM_name()+"님의 원활한 사이트 이용을 위하여, 아래의 링크를 클릭하여 인증해 주시길 부탁드립니다.<br /><br /><br /> "
					+ "<a href='http://203.236.209.145:8087/controller/upgradeMember.do?m_id="+m.getM_id()+"'>회원인증</a> <br /><br /> 항상 더 멋진 서비스로 보답을 하기 위해 노력하겠습니다. 감사합니다.";
			sender.send(m_email, title, content);
		}
		response.getWriter().print(re);
	}
	
	//회원 탈퇴 페이지로 간다.
	@RequestMapping(value="leaveMember.do",method=RequestMethod.GET)
	public ModelAndView leaveForm(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("myInfoViewPage", "leaveMember.jsp");
		mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//회원 탈퇴 처리를 한다.
	@RequestMapping(value="leaveMemberOk.do")
	public ModelAndView leaveSubmit(HttpSession session){
		ModelAndView mav = new ModelAndView();
		LoginMemberVo m = (LoginMemberVo)session.getAttribute("lm");
		int re = dao.deleteMember(m.getM_id());
		if(re == 1){
			mav.addObject("msg", "탈퇴 성공");
			mav.setViewName("redirect:/front.do");
			session.invalidate();
		}
		else{
			mav.addObject("msg", "탈퇴 실패");
			mav.addObject("myInfoViewPage", "resultPage.jsp");
			mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
			mav.setViewName("main");
		}
		return mav;
	}
}