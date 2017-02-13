package com.hanb.dao;


import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.vo.PostVo;

@Repository
public class NoticeDao {

	
	private static SqlSessionFactory factory=null;
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
		
	public int deleteNotice(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re =session.delete("board.deleteNotice",p_no);
		session.close();
		return re;
	}
	

	public int updateNotice(PostVo bp) {
		
		SqlSession session = factory.openSession(true);
		int re =session.update("board.updateNotice", bp);
		session.close();
		return re;
	}
	
	
	public int insertNotice(PostVo bp)
	{
		SqlSession session = factory.openSession(true);
		int re = session.insert("board.insertNotice", bp);
		session.close();
		return re;
	}
	
	public PostVo getNotice(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		PostVo p = session.selectOne("board.getNotice", p_no);
		session.close();
		return p;
	}
	
	
}
