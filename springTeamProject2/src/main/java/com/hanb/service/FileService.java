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
			System.out.println("���� ���ε� ����" + e);
		}
		return responseText;
	}

	/**
	 * �ش� p_no �� ��ϵǾ��ִ� ���ϵ��� �����´�
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
	 * ���ϵ��� �ӽ��������� ���ó�¥�� ���������� �̵���Ű�� files ���̺� �ش� p_no�� ������Ʈ �Ѵ�.
	 * 
	 * @param f_name
	 *            ������ ����Ǵ� ���� �̸�
	 * @param p_no
	 *            �۹�ȣ
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
	 * ���� �����θ� ���ó�¥�� �ٲ۴�
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
	 * ���� �����θ� ���ó�¥�� �ٲ۴�
	 * 
	 * @param f_names
	 * @param p_no
	 * @return
	 */
	public List<FileVo> moveFile(String[] f_names, int p_no) {
		List<FileVo> newFiles = new LinkedList<FileVo>();
		List<FileVo> files = dao.getFiles(f_names);
		for (FileVo f : files) {
			// ���� ����Ǿ� �ִ� ���� ���
			String tmpPath = f.getF_savepath();
			// upload ������ �ִ� ���
			UploadPath = f.getF_savepath().substring(0, tmpPath.lastIndexOf("\\"));
			// �Ȱܰ� ���� ���
			File savePath = new File(UploadPath + "/" + f_savePath);
			if (!savePath.exists()) {
				// �ش� ������ ���� ���� �ʱ⶧���� ���� ����
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
				System.out.println("���� �ű�� ���� " + e);
			}
		}
		return newFiles;
	}

}
