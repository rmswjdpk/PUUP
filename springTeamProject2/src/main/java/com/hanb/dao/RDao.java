package com.hanb.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.enums.Board;

@Repository
public class RDao {
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
	
	public Map<String,String> getMemberContractCount(String m_id){
		SqlSession session = factory.openSession();
		Map<String,String> map = session.selectOne("R.selectMemberContractReport",m_id);
		session.close();
		return map;
	}
	
	public Map<String, Integer> getMonthsReport(){
		SqlSession session = factory.openSession();
		Map<String,Integer> map = session.selectOne("R.selectMonthsReport");
		session.close();
		return map;
	}

	public List<Map<String, Object>> wordCloud(Board board) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("b_no", board.getBoardNum());
		List<Map<String, Object>> wordCloud = session.selectList("R.getWordCloud",map);
		session.close();
		return wordCloud;
	}

	public Map<String, Object> avgDate() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		Map<String, Object> avgDate = session.selectOne("R.getAvgDate");
		session.close();
		return avgDate;
	}

	public List<Map<String, Object>> ageList() {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<Map<String, Object>> list = session.selectList("R.getAgeList");
		session.close();
		return list;
	}
}
