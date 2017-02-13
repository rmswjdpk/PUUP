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

import com.hanb.enums.Board;
import com.hanb.vo.PostVo;
import com.hanb.vo.SearchVo;

/**
 * @author hb4010
 *
 */
@Repository
public class PostDao {
	private static SqlSessionFactory factory = null;
	static {
		try{
			Reader reader = Resources.getResourceAsReader("com/hanb/data/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e){System.out.println(e);}
	}
	/*총 게시글 개수*/
	public static int totalPost = 0;
	/*한 페이지에 보여줄 게시글 개수*/
	public static int showPost = 10;
	/*총 페이지 개수*/
	public static int totalPage = 0;
	/*한번에 보여줄 페이지 개수*/
	public static int pageGroup = 5;
	/*현재 페이지*/
	public static int pageNum = 0;
	
	public List<Map<String, String>> getMyActionList(String m_id){
		SqlSession session = factory.openSession();
		List<Map<String, String>> map = session.selectList("post.selectMyActiveList",m_id);
		session.close();
		return map;
	}
	
	public List<Map<String, String>> getListPost(int b_no,int pageNum, Board board){
		return getListPost(b_no, pageNum,new SearchVo(), board);
	}
	
	public List<Map<String, String>> getListPost(int b_no, int pageNum, SearchVo s, Board board){
		PostDao.pageNum = pageNum;
		int end = pageNum * showPost;
		int start = end - showPost + 1;
		s.setStart(start);
		s.setEnd(end);
		s.setB_no(b_no);
		
		totalPost = getCountPost(s);
		totalPage = (int)Math.ceil((double)totalPost / showPost);
		SqlSession session = factory.openSession();
		List<Map<String, String>> list = null;
		if(board.equals(Board.QandA))
			list = session.selectList("QandA.selectAll",s);
		else
			list = session.selectList("post.selectAll",s);
		session.close();
		return list;
	}
	
	public int getCountPost(SearchVo s){
		SqlSession session = factory.openSession();
		totalPost = session.selectOne("post.countPost",s);
		session.close();
		return totalPost;
	}
	
	public PostVo detailPost(int p_no){
		SqlSession session = factory.openSession();
		PostVo p = session.selectOne("post.detailPost",p_no);
		session.close();
		return p;
	}
	
	/**
	 * Post 테이블에 데이터를 등록한다.
	 * @param p_no
	 * @return 등록된 게시글 번호
	 */
	public int insertPost(PostVo p) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.insert("post.insertPost",p);
		int p_no = 0;
		if(re == 1){
			p_no = p.getP_no();
		}
		session.close();
		return p_no;
	}
	
	public int deletePost(int p_no){
		SqlSession session = factory.openSession(true);
		int re  = session.delete("post.deletePost",p_no);
		session.close();
		return re;
	}

	/**
	 * 해당 글 번호로 조회수를 1 증가 시킨다
	 * @param p_no
	 * @return 업데이트된 행의 개수
	 */
	public int updatePostHit(int p_no)
	{
		SqlSession session = factory.openSession(true);
		int re = session.update("post.updateHit",p_no);
		session.close();
		return re;
	}
	
	/**
	 * 해당 글 번호로 게시글 수정
	 * @param p(업데이트할 게시물 내용)
	 * @return 업데이트된 행의 개수
	 */
	public int updatePost(PostVo p) {
		// TODO Auto-generated method stub
		SqlSession session = factory.openSession(true);
		int re  = session.delete("post.updatePost",p);
		session.close();
		return re;
	}
}
