package com.hanb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.dao.DirectorRequestDao;
import com.hanb.dao.PostDao;
import com.hanb.dao.PupRequestDao;
import com.hanb.vo.PupRequestVo;

@Controller
public class PupRequestController {

	@Autowired
	private PupRequestDao dao;
	@Autowired
	private DirectorRequestDao drdao;
	@Autowired
	private PostDao pdao;
	
	@RequestMapping("mylistPupRequest.do")
	public ModelAndView mylist(String m_id)
	{
		ModelAndView view = new ModelAndView();
		view.addObject("sysdate", drdao.getSysdate());
		view.addObject("puList", dao.mylist(m_id));
		view.addObject("viewPage", "member/myInfo/myInfoFrame.jsp");
		view.addObject("myInfoViewPage", "listPupRequest.jsp");
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping(value="insertPupRequest.do", method=RequestMethod.GET)
	public ModelAndView insert_form()
	{
		ModelAndView view = new ModelAndView();
		view.addObject("viewPage", "pup/insertPupRequest.jsp");
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping(value="insertPupRequest.do", method=RequestMethod.POST)
	public ModelAndView insert_submit(PupRequestVo pr)
	{
		ModelAndView view = new ModelAndView();
		dao.insert(pr);
		view.setViewName("redirect://front.do");
		return view;
	}
	
	@RequestMapping("listPupRequest.do")
	public ModelAndView list()
	{
		ModelAndView view = new ModelAndView();
		view.addObject("viewPage", "pup/listPupRequest.jsp");
		view.addObject("puList", dao.list());
		view.addObject("sysdate", drdao.getSysdate());
		view.setViewName("main");
		return view;
	}
	
	@RequestMapping("detailPupRequest.do")
	public ModelAndView detail(int p_no)
	{
		ModelAndView view = new ModelAndView();
		pdao.updatePostHit(p_no);
		view.addObject("viewPage", "pup/detailPupRequest.jsp");
		view.addObject("pup", dao.getPup(p_no));
		view.setViewName("main");
		return view;
	}
}
