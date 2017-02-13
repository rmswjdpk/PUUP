package com.hanb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hanb.service.FileService;
import com.hanb.vo.FileVo;

@Controller
public class FileController {
	
	@Autowired
	private FileService service;
	
	@RequestMapping(value="uploadFile.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String uploadFile(HttpSession session,MultipartFile uploadFile){
		String responseText = "";
		if(uploadFile != null && uploadFile.getSize() != 0){
			String path = session.getServletContext().getRealPath("resources/upload/tmp");
			responseText = service.uploadFile(path, uploadFile);
		}
		else{
			responseText = "업로드에 실패하였습니다. 다시 시도해 주세요";
		}
		return responseText;
	}
	
	@RequestMapping("deleteFile.do")
	@ResponseBody
	public void deleteFile(HttpServletResponse response,String f_name) throws IOException{
		FileVo f = service.getFile(Integer.parseInt(f_name));
		int re = service.deleteFile(Integer.parseInt(f_name), f.getF_savepath());
		if(re == 1)
			response.getWriter().print(true);
		else
			response.getWriter().print(false);
	}
	
	@RequestMapping("downloadFile.do")
	public ModelAndView downloadFile(int f_name){
		FileVo f = service.getFile(f_name);
		return new ModelAndView("downloadView", "downloadFileVo", f);
	}
}