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

@Repository
public class QandADao {
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
	
	public List<Map<String,String>> getListQandA(){
		SqlSession session = factory.openSession();
		List<Map<String,String>> list =  session.selectOne("QandA.selectOneQandA");
		
		session.close();
		return list;
	}
	
	/**
	 * QandA 테이블 정보를 가져온다
	 * @param p_no
	 * @return map
	 * 	P_NO : 글 번호 <br>
	 *  P_DEEP : 글 들여쓰기 횟수 <br>
	 *  P_REF : 댓글의 부모글 <br>
	 *  P_STEP : 댓글 순서
	 */
	public Map<String,Integer> getDetailQandA(int p_no){
		SqlSession session = factory.openSession();
		Map<String, Integer> map = session.selectOne("QandA.selectOneQandA",p_no);
		session.close();
		return map;
	}

	public int insertQandA(int p_no, int p_ref, int p_deep, int p_step) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("p_no", p_no);
		map.put("p_ref", p_ref);
		map.put("p_deep", p_deep);
		map.put("p_step", p_step);
		SqlSession session = factory.openSession(true);
		int re = session.insert("QandA.insertQandA",map);
		session.close();
		return re;
	}
	
	/**
	 * 파라메터로 넘어온 p_ref 를 가지고 있는 모든 데이터들의 p_step중에 <br>
	 * 파라메터로 넘어온 p_step보다 큰 p_step들은 1씩 증가 시킨다 
	 * @param p_ref	부모글번호
	 * @param p_step 
	 * @return 업데이트된 행의 개수
	 */
	public int updateStep(int p_ref, int p_step){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("p_ref", p_ref);
		map.put("p_step", p_step);
		
		SqlSession session = factory.openSession(true);
		int re = session.insert("QandA.updateStepQandA",map);
		session.close();
		return re;
	}
}
