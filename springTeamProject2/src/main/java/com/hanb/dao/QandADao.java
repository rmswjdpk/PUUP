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
	 * QandA ���̺� ������ �����´�
	 * @param p_no
	 * @return map
	 * 	P_NO : �� ��ȣ <br>
	 *  P_DEEP : �� �鿩���� Ƚ�� <br>
	 *  P_REF : ����� �θ�� <br>
	 *  P_STEP : ��� ����
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
	 * �Ķ���ͷ� �Ѿ�� p_ref �� ������ �ִ� ��� �����͵��� p_step�߿� <br>
	 * �Ķ���ͷ� �Ѿ�� p_step���� ū p_step���� 1�� ���� ��Ų�� 
	 * @param p_ref	�θ�۹�ȣ
	 * @param p_step 
	 * @return ������Ʈ�� ���� ����
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
