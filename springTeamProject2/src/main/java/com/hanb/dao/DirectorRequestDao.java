package com.hanb.dao;

import java.io.Reader;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.enums.Board;
import com.hanb.vo.DirectorRequestVo;

@Repository
public class DirectorRequestDao {
	
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
	
	public int updateDate() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.update("d_req_post.updateDate");
		session.close();
		return re;
	}
	
	public int updateGrade(int dIR_RESTRICT) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.update("d_req_post.updateGrade", dIR_RESTRICT);
		session.close();
		return re;
	}
	
	public int insert(DirectorRequestVo dr)
	{
		int re = -1;
		SqlSession session = factory.openSession(true);
		dr.setB_no(Board.DRequest.getBoardNum());
		session.insert("post.insertPost", dr);
		session.insert("d_req_post.insert");
		String [] fname = dr.getF_name().split(",",3);
		//re = dao.moveFile(fname, dr.getP_no());
		session.close();
		return re;
	}
	
	public List<HashMap<String, Object>> list()
	{
		SqlSession session = factory.openSession();
		List<HashMap<String, Object>> list = session.selectList("d_req_post.selectAll");
		session.close();
		return list;
	}

	public HashMap<String, Object> detail(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		HashMap<String, Object> map = session.selectOne("d_req_post.getDir",p_no);
		session.close();
		return map;
	}
	
	public int vote(int p_no, String vote, String m_id)
	{
		SqlSession session = factory.openSession(true);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("p_no", p_no);
		map.put("vote", vote);
		map.put("m_id", m_id);
		int re = -1;
		try{
		re = session.insert("d_req_post.vote",map);
		if(re==1)
		{
			session.update("d_req_post.updateVote",map);
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		session.close();
		return re;
	}

	public List<HashMap<String, Object>> mylist(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<HashMap<String, Object>> list = session.selectList("d_req_post.mySelectAll",m_id);
		session.close();
		return list;
	}

	public Date getSysdate() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		Date sysdate = session.selectOne("post.getSysdate");
		session.close();
		return sysdate;
	}
}
