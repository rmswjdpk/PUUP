package com.hanb.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.vo.ReplyVo;

@Repository
public class ReplyDao {
	private static SqlSessionFactory factory = null;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/hanb/data/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<ReplyVo> getReplys(int p_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<ReplyVo> list = session.selectList("reply.selectAll",p_no);
		session.close();
		return list;
	}
	public int insertReply(ReplyVo r) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int insertedCount = session.insert("reply.insertReply",r);
		session.close();
		return insertedCount;
	}
	public int deleteReply(int r_no) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int deletedCount = session.delete("reply.deleteReply",r_no);
		session.close();
		return deletedCount;
	}
}
