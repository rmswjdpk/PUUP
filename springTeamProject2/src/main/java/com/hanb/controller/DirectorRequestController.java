package com.hanb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.DirectorRequestDao;
import com.hanb.dao.FileDao;
import com.hanb.dao.PostDao;
import com.hanb.vo.DirectorRequestVo;
import com.hanb.vo.LoginMemberVo;

@Controller
public class DirectorRequestController {

	@Autowired
	private DirectorRequestDao dao;
	@Autowired
	private FileDao fdao;
	@Autowired
	private PostDao pdao;
	public void setFdao(FileDao fdao) {
		this.fdao = fdao;
	}
	public void setDao(DirectorRequestDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="directorRequest.do", method=RequestMethod.GET)
	public ModelAndView insert_form()
	{
		ModelAndView view = new ModelAndView();
		view.addObject("viewPage", "director/directorRequest.jsp");
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping(value="directorRequest.do", method=RequestMethod.POST)
	public ModelAndView insert_submit(DirectorRequestVo dr)
	{
		ModelAndView view = new ModelAndView();
		dao.insert(dr);
		view.setViewName("redirect://front.do");
		return view;
	}
	
	@RequestMapping("listDirectorRequest.do")
	public ModelAndView list()
	{
		ModelAndView view = new ModelAndView();
		view.addObject("viewPage", "director/listDirectorRequest.jsp");
		view.addObject("dirList", dao.list());
		view.addObject("sysdate", dao.getSysdate());
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping("myListDirectorRequest.do")
	public ModelAndView mylist(HttpSession session)
	{
		ModelAndView view = new ModelAndView();
		LoginMemberVo lm=(LoginMemberVo)session.getAttribute("lm");
		String m_id=lm.getM_id();
		view.addObject("dirList", dao.mylist(m_id));
		view.addObject("sysdate", dao.getSysdate());
		
		view.addObject("myInfoViewPage", "listDirectorRequest.jsp");
		view.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		
//		view.addObject("viewPage", "director/listDirectorRequest.jsp");
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping("detailDirectorRequest.do")
	public ModelAndView detail(int p_no)
	{
		ModelAndView view = new ModelAndView();
		pdao.updatePostHit(p_no);
		view.addObject("viewPage", "director/detailDirectorRequest.jsp");
		view.addObject("dr", dao.detail(p_no));
		view.addObject("flist", fdao.getFiles(p_no));
		view.addObject("restrict",MainController.DIR_RESTRICT);
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping("updateVote.do")
	public ModelAndView vote(int p_no, String vote, HttpSession session)
	{
		ModelAndView view = new ModelAndView();
		LoginMemberVo m = (LoginMemberVo)session.getAttribute("lm");
		String m_id=m.getM_id();
		int re = dao.vote(p_no, vote,m_id);
		if(re==1)
		{
			view.setViewName("redirect:/detailDirectorRequest.do?p_no="+p_no);
		}
		else
		{
			view.addObject("viewPage", "director/error.jsp");
			view.addObject("msg", "이미 투표하셨습니다.");
			view.setViewName("main");
		}
		return view;
	}
}
