package com.hanb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanb.dao.PostDao;
import com.hanb.dao.QandADao;
import com.hanb.enums.Board;
import com.hanb.service.PostService;
import com.hanb.service.QandAService;
import com.hanb.vo.PostVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class QAtest {
	
	@Autowired private QandADao dao;
	@Autowired private QandAService service;

	@Autowired private PostDao pdao;
	@Autowired private PostService pservice;
	
	PostVo p;
	@Before
	public void setUp(){
		p = new PostVo();
		p.setB_no(Board.QandA.getBoardNum());
		p.setM_id("jyc228");
		p.setP_content("aa");
		p.setP_hit(0);
		p.setP_title("하이");
	}
	
	@Test
	public void insertDetailDeleteTest() {
		/*Dao 테스트*/
		int p_no = pdao.insertPost(p);
		int insertedCount = dao.insertQandA(p_no, p_no, 0, 0);
		/*인설트 결과가 1인지 확인*/
		assertThat(1, is(insertedCount));
		
		int deletedCount = pdao.deletePost(p_no);
		/*삭제 결과가 1인지 확인*/
		assertThat(1, is(deletedCount));
		
		/*실제로 삭제되어 해당 테이블에 자료가 없는것을 확인*/
		assertNull(dao.getDetailQandA(p_no));
		assertNull(pdao.detailPost(p_no));
	}
	
	@Test
	public void serviceTest() {
		/*Service 테스트*/
		int p_no = pservice.insertPost(p, Board.QandA);
		/*인설트 결과가 true 인지 확인*/
		assertTrue(service.insertQandA(p_no, null));
		
		int deletedCount = pservice.deletePost(p_no);
		/*삭제 결과가 1인지 확인*/
		assertThat(1, is(deletedCount));
		
		/*실제로 삭제되어 해당 테이블에 자료가 없는것을 확인*/
		assertNull(dao.getDetailQandA(p_no));
	}
}
