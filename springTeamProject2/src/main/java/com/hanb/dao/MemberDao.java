package com.hanb.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.hanb.vo.LoginMemberVo;
import com.hanb.vo.MemberVo;
import com.hanb.vo.SearchVo;

@Repository
public class MemberDao {
	private static SqlSessionFactory factory = null;
	static{
		try{
			Reader reader = Resources.getResourceAsReader("com/hanb/data/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e){System.out.println(e);}
	}
	
	
	//ȸ�� ���
	public List<Map<String, String>> listMember(SearchVo s) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<Map<String, String>> list = session.selectList("member.selectAll",s);
		session.close();
		return list;
	}
		
		
	// Ư������ �����ϴ��� Ȯ��
	public int checkValue(String value) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		
		String[] arr = value.split("=");
		SearchVo s = new SearchVo();
		s.setKeyField(arr[0]);
		s.setKeyWord(arr[1]);
		
		int re = session.selectOne("member.checkValue", s);
		session.close();
		return re;
	}
	
	// ȸ�� ��й�ȣ�� ����
	public String getPwd(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		String pwd = session.selectOne("member.getPwd", m_id);
		session.close();
		return pwd;
	}
	
	// �α��� �����Ұ�� ���ǿ� ���� ���̵�, ȸ����ް� ����̸��� ����
	public LoginMemberVo login(String m_id){
		SqlSession session = factory.openSession();
		LoginMemberVo m = session.selectOne("member.login", m_id);
		session.close();
		return m;
	}

	//ȸ�� �߰�
	public int insertMember(MemberVo m) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.insert("member.insertMember", m);
		session.close();
		return re;
	}
	
	//ȸ�� ����
	public int updateMember(MemberVo m) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.update("member.updateMember", m);
		session.close();
		return re;
	}

	//ȸ�� ����
	public int deleteMember(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.delete("member.deleteMember", m_id);
		session.close();
		return re;
	}
	
	//ȸ�� ��
	public MemberVo detailMember(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.selectMember", m_id);
		session.close();
		return m;
	}
	
	public String getMemberId(String m_email){
		SqlSession session = factory.openSession();
		String m_id = session.selectOne("member.selectMemberId", m_email);
		session.close();
		return m_id;
	}

	
	//ȸ�� ��� ���
	public List<Map<Integer, String>> listGrade(){
		SqlSession session = factory.openSession();
		List<Map<Integer, String>> list = session.selectList("member.grade");
		session.close();
		return list;
	}
	
	
	//�� ȸ����
	public int getCountMember(){
		SqlSession session = factory.openSession();
		int re = session.selectOne("member.selectCount");
		session.close();
		return re;
	}
	
	public int getCountMember(SearchVo s){
		SqlSession session = factory.openSession();
		int re = session.selectOne("member.selectCount",s);
		session.close();
		return re;
	}
	
	public List<Map<String, String>> getCountEachGradeMember(){
		SqlSession session = factory.openSession();
		List<Map<String, String>> list = session.selectList("member.selectMemberCount");
		session.close();
		return list;
	}


	public int checkIdPwd(MemberVo m) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		int re = session.selectOne("member.checkIdEmail",m);
		session.close();
		return re;
	}


	public List<Map<String,String>> getMonthNewMember(int todayMonth) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<Map<String,String>> list = session.selectList("member.selectMonthNewMember",todayMonth);
		session.close();
		return list;
	}
}
