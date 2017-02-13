package com.hanb.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.service.FileService;
import com.hanb.vo.FileVo;

@Repository
public class FileDao {
	private static SqlSessionFactory factory = null;
	static{
		try{
			Reader reader = Resources.getResourceAsReader("com/hanb/data/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e){System.out.println(e);}
	}
	private static final int TMP_FILE_SAVE_PERIOD = 2;
	
	public int insertFile(FileVo f) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.insert("file.insert", f);
		session.close();
		return re;
	}
	
	public int deleteFile(int f_name) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("columnName", "f_name");
		map.put("value", String.valueOf(f_name));
		return deletetFile(map);
	}
	
	public int deleteFiles(int p_no) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("columnName", "p_no");
		map.put("value", String.valueOf(p_no));
		return deletetFile(map);
	}
	
	private int deletetFile(Map<String,String> map){
		SqlSession session = factory.openSession(true);
		int re = session.delete("file.deleteFile", map);
		session.close();
		return re;
	}
	
	public int deleteTmpFile(List<FileVo> list) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.delete("file.deleteTmp", list);
		session.close();
		return re;
	}
	
	public List<FileVo> getTmpFiles(String time){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", time);
		map.put("save_period", TMP_FILE_SAVE_PERIOD);
		SqlSession session = factory.openSession();
		List<FileVo> list = session.selectList("file.selectTmp", map);
		session.close();
		return list;
	}

	public int updateFile(FileVo f){
		SqlSession session = factory.openSession(true);
		int re = session.update("file.update", f);
		session.close();
		return re;
	}
	
	public int updateFileP_no(List<FileVo> files, int p_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", p_no);
		map.put("list", files);
		map.put("savePath", FileService.UploadPath+"/"+FileService.f_savePath);
		SqlSession session = factory.openSession(true);
		int re = session.update("file.updateFileP_no", map);
		session.close();
		return re;
	}

	public FileVo getFile(int f_name) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		FileVo f = session.selectOne("file.selectFile", f_name);
		session.close();
		return f;
	}
	
	public List<FileVo> getFiles(int p_no){
		String[] values = new String[1];
		values[0] = String.valueOf(p_no);
		return getFiles("p_no",values);
	}
	
	public List<FileVo> getFiles(String[] f_names) {
		return getFiles("f_name",f_names);
	}
	
	private List<FileVo> getFiles(String columnName, Object[] values) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		map.put("columnName", columnName);
		for(Object f_name : values){
			Map<String,Object> innerMap = new HashMap<String,Object>();
			innerMap.put("value", f_name);
			list.add(innerMap);
		}
		map.put("list", list);
		SqlSession session = factory.openSession();
		List<FileVo> fileList = session.selectList("file.selectFiles", map);
		session.close();
		return fileList;
	}
	
}
