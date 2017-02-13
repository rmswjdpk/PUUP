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
	
	
	//회원 목록
	public List<Map<String, String>> listMember(SearchVo s) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		List<Map<String, String>> list = session.selectList("member.selectAll",s);
		session.close();
		return list;
	}
		
		
	// 특정값이 존재하는지 확인
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
	
	// 회원 비밀번호를 얻어옴
	public String getPwd(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession();
		String pwd = session.selectOne("member.getPwd", m_id);
		session.close();
		return pwd;
	}
	
	// 로그인 성공할경우 세션에 담을 아이디, 회원등급과 등급이름을 얻어옴
	public LoginMemberVo login(String m_id){
		SqlSession session = factory.openSession();
		LoginMemberVo m = session.selectOne("member.login", m_id);
		session.close();
		return m;
	}

	//회원 추가
	public int insertMember(MemberVo m) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re = session.insert("member.insertMember", m);
		session.close();
		return re;
	}
	
	//회원 수정
	public int updateMember(MemberVo m) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.update("member.updateMember", m);
		session.close();
		return re;
	}

	//회원 삭제
	public int deleteMember(String m_id) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.delete("member.deleteMember", m_id);
		session.close();
		return re;
	}
	
	//회원 상세
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

	
	//회원 등급 목록
	public List<Map<Integer, String>> listGrade(){
		SqlSession session = factory.openSession();
		List<Map<Integer, String>> list = session.selectList("member.grade");
		session.close();
		return list;
	}
	
	
	//총 회원수
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
