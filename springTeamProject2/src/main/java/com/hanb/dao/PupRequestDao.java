package com.hanb.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.enums.Board;
import com.hanb.vo.PupRequestVo;

@Repository
public class PupRequestDao {
	
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
	
	public List<PupRequestVo> mylist(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<PupRequestVo> list = session.selectList("pupRequest.mylist",m_id);
		session.close();
		return list;
	}
	
	public int updateDate() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.update("pupRequest.updateDate");
		session.close();
		return re;
		
	}
	
	public int insert(PupRequestVo pr)
	{
		SqlSession session = factory.openSession(true);
		pr.setB_no(Board.PUP.getBoardNum());
		int re = session.insert("post.insertPost", pr);
		if(re==1)
		{
			session.insert("pupRequest.insert", pr);
			//re = dao.moveFile(pr.getF_name(), pr.getP_no());
		}
		session.close();
		return re;
	}

	public List<PupRequestVo> list() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<PupRequestVo> list = session.selectList("pupRequest.list");
		session.close();
		return list;
	}

	public PupRequestVo getPup(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		PupRequestVo pr = session.selectOne("pupRequest.getPup",p_no);
		session.close();
		return pr;
	}
}
