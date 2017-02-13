package com.hanb.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.enums.Board;
import com.hanb.service.FileService;
import com.hanb.service.PostService;
import com.hanb.service.ReplyService;
import com.hanb.vo.FileVo;
import com.hanb.vo.PostVo;

@Controller
public class FreeController {

	@Autowired
	private FileService fileService;
	@Autowired
	private PostService service;
	@Autowired
	private ReplyService rService;
	
	private ModelAndView setViewPage(String viewPage, ModelAndView mav){
		mav.addObject("viewPage", "board/free/"+viewPage);
		mav.setViewName("main");;
		return mav;
	}
	
	@RequestMapping("listFree.do")
	public ModelAndView listFree(String pageNum){
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.getListPost(Board.Free,pageNum));
		mav.addObject("pageStr",service.getPageStr(Board.Free));
		return setViewPage("listFree.jsp", mav);
	}
	
	@RequestMapping("detailFree.do")
	public ModelAndView detailFree(int p_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("p", service.detailPost(p_no,true));
		mav.addObject("files", fileService.getFileList(p_no));
		mav.addObject("rlist", rService.getReplys(p_no));
		return setViewPage("detailFree.jsp", mav);
	}
	
	@RequestMapping("deleteFree.do")
	public ModelAndView deleteFree(int p_no){
		ModelAndView mav = new ModelAndView();
		service.deletePost(p_no);
		List<FileVo> list = fileService.getFileList(p_no);
		if(list.size()!=0){
			fileService.deleteFiles(list,p_no);
		}
		mav.setViewName("redirect:/listFree.do");;
		return mav;
	}
	
	@RequestMapping(value="updateFree.do",method=RequestMethod.GET)
	public ModelAndView updateFreeForm(int p_no){
		ModelAndView mav = new ModelAndView();
		mav.addObject("p",service.detailPost(p_no,false));
		mav.addObject("files",fileService.getFileList(p_no));
		return setViewPage("updateFree.jsp", mav);
	}
	
	@RequestMapping(value="updateFree.do",method=RequestMethod.POST)
	public ModelAndView updateFreeSubmit(PostVo p,String[] f_name){
		ModelAndView mav = new ModelAndView();
		int re = service.updatePost(p,p.getP_no());
		if(re == 1){
			if(f_name != null)
				fileService.updateFileP_no(f_name, p.getP_no());
			System.out.println("수정 성공");
		}
		else{
			System.out.println("수정 실패");
		}
		mav.setViewName("redirect:/listFree.do");;
		return mav;
	}
	
	@RequestMapping(value="insertFree.do",method=RequestMethod.GET)
	public ModelAndView insertFreeForm(){
		ModelAndView mav = new ModelAndView();
		return setViewPage("insertFree.jsp", mav);
	}
	
	@RequestMapping(value="insertFree.do",method=RequestMethod.POST)
	public ModelAndView insertFreeSubmit(PostVo p,String[] f_name){
		ModelAndView mav = new ModelAndView();
		int p_no = service.insertPost(p, Board.Free);
		if(f_name != null && p_no != 0){
			fileService.updateFileP_no(f_name, p_no);
		}
		else{
			System.out.println("등록 실패");
		}
		mav.setViewName("redirect:/listFree.do");;
		return mav;
	}
}
