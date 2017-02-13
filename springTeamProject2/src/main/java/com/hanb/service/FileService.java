package com.hanb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanb.dao.FileDao;
import com.hanb.vo.FileVo;

@Service
public class FileService {

	@Autowired
	private FileDao dao;

	public static String f_savePath = null;
	public static String UploadPath = null;

	public String uploadFile(String tmpPath, MultipartFile uploadFile) {
		String responseText = "";
		int filename = (int) System.currentTimeMillis();
		try {
			FileOutputStream out = new FileOutputStream(tmpPath + "/" + filename);
			out.write(uploadFile.getBytes());
			out.flush();
			out.close();

			FileVo f = new FileVo(filename, uploadFile.getOriginalFilename(), tmpPath, 0);
			dao.insertFile(f);

			ObjectMapper mapper = new ObjectMapper();
			responseText = mapper.writeValueAsString(f);
		} catch (IOException e) {
			System.out.println("파일 업로드 에러" + e);
		}
		return responseText;
	}

	/**
	 * 해당 p_no 로 등록되어있는 파일들을 가져온다
	 * 
	 * @param p_no
	 * @return
	 */
	public List<FileVo> getFileList(int p_no) {
		return dao.getFiles(p_no);
	}

	public FileVo getFile(int f_name) {
		return dao.getFile(f_name);
	}

	public int insertFile(FileVo f) {
		return dao.insertFile(f);
	}

	public int insertFile(FileVo f, int p_no) {
		f.setP_no(p_no);
		return dao.insertFile(f);
	}

	/**
	 * 파일들을 임시폴더에서 오늘날짜의 저장폴더로 이동시키고 files 테이블에 해당 p_no로 업데이트 한다.
	 * 
	 * @param f_name
	 *            서버에 저장되는 파일 이름
	 * @param p_no
	 *            글번호
	 * @return
	 */
	public int updateFileP_no(String[] f_name, int p_no) {
		List<FileVo> files = moveFile(f_name, p_no);
		return dao.updateFileP_no(files, p_no);
	}

	public int deleteFile(int f_name, String f_savePath) {
		FileVo f = new FileVo(f_name, f_savePath);
		boolean b = deleteServerFile(f);
		if (b) {
			return dao.deleteFile(f_name);
		} else {
			return 0;
		}
	}

	public int deleteFiles(List<FileVo> files, int p_no) {
		boolean b = deleteServerFiles(files);
		if (b) {
			return dao.deleteFiles(p_no);
		} else {
			return 0;
		}
	}

	public static String getSavePathName() {
		LocalDateTime now = LocalDateTime.now();
		String savePath = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
		f_savePath = savePath;
		return savePath;
	}

	private boolean deleteServerFile(FileVo f) {
		List<FileVo> list = new ArrayList<FileVo>();
		list.add(f);
		return deleteServerFiles(list);
	}

	private boolean deleteServerFiles(List<FileVo> files) {
		boolean b = false;
		for (FileVo f : files) {
			File file = new File(f.getF_savepath() + "/" + f.getF_name());
			b = file.delete();
		}
		return b;
	}

	/**
	 * 파일 저장경로를 오늘날짜로 바꾼다
	 * 
	 * @param f_name
	 * @param p_no
	 * @return
	 */
	public List<FileVo> moveFile(int f_name, int p_no) {
		String[] fnames = new String[1];
		fnames[0] = String.valueOf(f_name);
		return moveFile(fnames, p_no);
	}

	/**
	 * 파일 저장경로를 오늘날짜로 바꾼다
	 * 
	 * @param f_names
	 * @param p_no
	 * @return
	 */
	public List<FileVo> moveFile(String[] f_names, int p_no) {
		List<FileVo> newFiles = new LinkedList<FileVo>();
		List<FileVo> files = dao.getFiles(f_names);
		for (FileVo f : files) {
			// 현재 저장되어 있는 폴더 경로
			String tmpPath = f.getF_savepath();
			// upload 폴더가 있는 경로
			UploadPath = f.getF_savepath().substring(0, tmpPath.lastIndexOf("\\"));
			// 옴겨갈 폴더 경로
			File savePath = new File(UploadPath + "/" + f_savePath);
			if (!savePath.exists()) {
				// 해당 폴더가 존재 하지 않기때문에 새로 생성
				savePath.mkdirs();
			}
			Path oldPath = Paths.get(tmpPath + "/" + f.getF_name());
			Path newPath = Paths.get(savePath.getPath() + "/" + f.getF_name());

			try {
				Files.move(oldPath, newPath, StandardCopyOption.ATOMIC_MOVE);
				f.setF_savepath(savePath.toString());
				f.setP_no(p_no);
				newFiles.add(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("파일 옮기기 실패 " + e);
			}
		}
		return newFiles;
	}

}
