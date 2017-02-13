package com.hanb.schedule;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.hanb.dao.FileDao;
import com.hanb.vo.FileVo;

@Controller
public class FileSchedule {

	@Autowired
	private FileDao dao;
	
	@Scheduled(cron="0 0 16 * * *")
	public void deleteFile(){
		LocalDateTime now = LocalDateTime.now();
		String time = now.getYear()+"/"+now.getMonthValue()+"/"+now.getDayOfMonth();
		List<FileVo> list = dao.getTmpFiles(time);
		if(list.size() != 0){
			int re = dao.deleteTmpFile(list);
			if(re != 0){
				for(FileVo f : list){
					File file = new File(f.getF_savepath()+"/"+f.getF_name());
					file.delete();
				}
				System.out.println("삭제된 임시 파일 개수 " + re);
			}
		}
	}
}