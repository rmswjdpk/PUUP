package com.hanb.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.enums.Board;
import com.hanb.service.PostService;
import com.hanb.vo.PostVo;

@Controller
public class NoticeController {

	@Autowired
	private PostService service;

	@RequestMapping(value = "updateNotice.do", method = RequestMethod.GET)
	public ModelAndView update(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.detailPost(p_no, false));
		mav.addObject("viewPage", "board/notice/updateNotice.jsp");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "updateNotice.do", method = RequestMethod.POST)
	public ModelAndView update_submit(PostVo p) {
		ModelAndView mav = new ModelAndView();
		int re = service.updatePost(p, p.getP_no());
		if(re == 1){
			mav.setViewName("redirect:/listNotice.do");
		}
		else{
			mav.setViewName("redirect:/crudError.do");
		}
		return mav;
	}

	@RequestMapping(value = "deleteNotice.do", method = RequestMethod.GET)
	public ModelAndView delete(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.deletePost(p_no));
		mav.setViewName("redirect:/listNotice.do");
		return mav;
	}

	@RequestMapping(value = "insertNotice.do", method = RequestMethod.GET)
	public ModelAndView insert_form() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewPage", "board/notice/insertNotice.jsp");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "insertNotice.do", method = RequestMethod.POST)
	public ModelAndView insert_submit(PostVo p, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int re = service.insertPost(p, Board.Notice);
		if(re != 0){
			mav.setViewName("redirect:/listNotice.do");
		}
		else{
			mav.setViewName("redirect:/crudError.do");
		}
		return mav;
	}

	@RequestMapping("listNotice.do")
	public ModelAndView list(String pageNum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.getListPost(Board.Notice,pageNum));
		mav.addObject("pageStr",service.getPageStr(Board.Notice));
		mav.addObject("viewPage", "board/notice/listNotice.jsp");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping("detailNotice.do")
	public ModelAndView detail(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("de", service.detailPost(p_no,true));
		mav.addObject("viewPage", "board/notice/detailNotice.jsp");
		mav.setViewName("main");
		return mav;
	}
}
