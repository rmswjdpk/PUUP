package com.hanb.dao;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.vo.PupReplyVo;

@Repository
public class PupReplyDao {

	private static SqlSessionFactory factory = null;
	static{
		try{
			Reader reader = Resources.getResourceAsReader("com/hanb/data/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	
	public int insert(PupReplyVo prv)
	{
		SqlSession session = factory.openSession(true);
		 int re = session.insert("pupReply.insertReply", prv);
		if(re==1)
		{
			session.insert("pupReply.insertChoice");
			session.insert("file.updatePR", prv);
		}
		session.close();
		return re;
	}


	public List<Map<String,String>> list(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<Map<String,String>> list = session.selectList("pupReply.list",p_no);
		session.close();
		return list;
	}


	public int updateChioce(int p_no, int r_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		session.update("pupReply.complete",p_no);
		int re =session.update("pupReply.updateChoice", r_no);
		session.update("pupRequest.updateCategory",p_no);
		session.close();
		return re;
	}
	
	public int productInsert(PupReplyVo prv) {
		SqlSession session = factory.openSession(true);
		 int re = session.insert("pupReply.insertReply", prv);
		if(re==1)
		{
			session.insert("pupReply.insertProductChoice");
			session.insert("file.updatePR", prv);
		}
		session.close();
		return re;
		
	}
	
}
