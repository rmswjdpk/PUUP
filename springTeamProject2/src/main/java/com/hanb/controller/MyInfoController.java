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
	
	//�������� ����������
	//�� Ȱ��������� �����̷�Ʈ �ȴ�.
	@RequestMapping("myInfo.do")
	public ModelAndView myInfo(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/myActionList.do");
		return mav;
	}
	
	//���� Ȱ�� ������� ����. ���� ������!!!!!!!!!!!!!!!!!!!
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
	
	//�ϳ��� �������ִ� �ּҸ� �����ȣ/�ּ�/���ּҷ� �����Ͽ� mav�� �ִ´�.
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
	
	//�������� ���� �������� ����
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
	
	//�������� ���������� ������Ʈ �Ѵ�
	@RequestMapping(value="updateMyInfo.do",method=RequestMethod.POST)
	public ModelAndView updateSubmit(HttpSession session,MemberVo m){
		ModelAndView mav = new ModelAndView();
		if(m.getM_pwd().length() > 1){
			PrivateKey privateKey = (PrivateKey)session.getAttribute("__privateKey__");
			m.setM_pwd(BCrypt.hashpw(rsa.decryptRsa(m.getM_pwd(), privateKey), BCrypt.gensalt(12)));
		}
		int re = dao.updateMember(m);
		if(re == 1){
			mav.addObject("msg", "���� ����");
		}
		else{
			mav.addObject("msg", "���� ����");
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
			String title = "PUP ȸ���� �̸��� ������ �ȳ�";
			String content = "<h2>�ȳ��Ͻʴϱ�? "+m.getM_name()+"��</h2><br /> "
					+ "���� ����Ʈ�� �̸����� ���� ȸ�� ������ ���� ������ ��Ȱ�� �̿��� �Ұ��� �մϴ�. �׷��� ������ "+m.getM_name()+"���� ��Ȱ�� ����Ʈ �̿��� ���Ͽ�, �Ʒ��� ��ũ�� Ŭ���Ͽ� ������ �ֽñ� ��Ź�帳�ϴ�.<br /><br /><br /> "
					+ "<a href='http://203.236.209.145:8087/controller/upgradeMember.do?m_id="+m.getM_id()+"'>ȸ������</a> <br /><br /> �׻� �� ���� ���񽺷� ������ �ϱ� ���� ����ϰڽ��ϴ�. �����մϴ�.";
			sender.send(m_email, title, content);
		}
		response.getWriter().print(re);
	}
	
	//ȸ�� Ż�� �������� ����.
	@RequestMapping(value="leaveMember.do",method=RequestMethod.GET)
	public ModelAndView leaveForm(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("myInfoViewPage", "leaveMember.jsp");
		mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//ȸ�� Ż�� ó���� �Ѵ�.
	@RequestMapping(value="leaveMemberOk.do")
	public ModelAndView leaveSubmit(HttpSession session){
		ModelAndView mav = new ModelAndView();
		LoginMemberVo m = (LoginMemberVo)session.getAttribute("lm");
		int re = dao.deleteMember(m.getM_id());
		if(re == 1){
			mav.addObject("msg", "Ż�� ����");
			mav.setViewName("redirect:/front.do");
			session.invalidate();
		}
		else{
			mav.addObject("msg", "Ż�� ����");
			mav.addObject("myInfoViewPage", "resultPage.jsp");
			mav.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
			mav.setViewName("main");
		}
		return mav;
	}
}