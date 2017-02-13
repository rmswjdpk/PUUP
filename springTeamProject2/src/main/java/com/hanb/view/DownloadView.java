package com.hanb.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.hanb.vo.FileVo;

public class DownloadView extends AbstractView {
	public DownloadView(){
		setContentType("application/download;charset=UTF-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		FileVo f = (FileVo)model.get("downloadFileVo");
		File file = new File(f.getF_savepath()+"/"+f.getF_name());
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String filename = URLEncoder.encode(f.getF_realname(), "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(file);
		FileCopyUtils.copy(in, out);
		in.close();
		out.close();
	}
}