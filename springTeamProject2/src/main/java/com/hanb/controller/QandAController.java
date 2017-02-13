package com.hanb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.enums.Board;
import com.hanb.service.FileService;
import com.hanb.service.PostService;
import com.hanb.service.QandAService;
import com.hanb.vo.PostVo;

@Controller
public class QandAController {

	@Autowired
	private PostService service;
	@Autowired
	private QandAService qService;
	@Autowired
	private FileService fileService;
	
	private ModelAndView setViewPage(String viewPage, ModelAndView mav){
		mav.addObject("viewPage", "board/QandA/"+viewPage);
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "updateQandA.do", method = RequestMethod.GET)
	public ModelAndView update(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.detailPost(p_no, false));
		mav.addObject("files", fileService.getFileList(p_no));
		return setViewPage("updateQandA.jsp",mav);
	}

	@RequestMapping(value = "updateQandA.do", method = RequestMethod.POST)
	public ModelAndView update_submit(PostVo p, String[] f_name) {
		ModelAndView mav = new ModelAndView();
		int re = service.updatePost(p, p.getP_no());
		if (re == 1) {
			if (f_name != null)
				fileService.updateFileP_no(f_name, p.getP_no());
			mav.setViewName("redirect:/listQandA.do");
		} else {
			mav.setViewName("redirect:/crudError.do");
		}
		return mav;
	}

	@RequestMapping(value = "deleteQandA.do", method = RequestMethod.GET)
	public ModelAndView delete(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.deletePost(p_no));
		mav.setViewName("redirect:/listQandA.do");
		return mav;
	}

	@RequestMapping(value = "insertQandA.do", method = RequestMethod.GET)
	public ModelAndView insert_form(String p_ref) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p_ref", p_ref);
		return setViewPage("insertQandA.jsp",mav);
	}

	@RequestMapping(value = "insertQandA.do", method = RequestMethod.POST)
	public ModelAndView insert_submit(String p_ref, PostVo p, String[] f_name) {
		ModelAndView mav = new ModelAndView();
		int inserted_P_no = service.insertPost(p, Board.QandA);
		if (inserted_P_no != 0) {
			/*p_ref 가 0이면 최상위 부모글이고 특정 숫자가 있으면 그 글의 리플이 된다*/
			qService.insertQandA(inserted_P_no, p_ref);
			
			if (f_name != null)
				fileService.updateFileP_no(f_name, inserted_P_no);
			
			mav.setViewName("redirect:/listQandA.do");
		} else {
			mav.setViewName("redirect:/crudError.do");
		}
		return mav;
	}

	@RequestMapping("listQandA.do")
	public ModelAndView list(String pageNum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.getListPost(Board.QandA, pageNum));
		mav.addObject("pageStr", service.getPageStr(Board.QandA));
		return setViewPage("listQandA.jsp",mav);
	}

	@RequestMapping("detailQandA.do")
	public ModelAndView detail(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.detailPost(p_no, true));
		mav.addObject("files", fileService.getFileList(p_no));
		return setViewPage("detailQandA.jsp",mav);
	}
}
