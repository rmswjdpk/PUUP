package com.hanb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanb.dao.ReplyDao;
import com.hanb.vo.ReplyVo;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao dao;
	
	/**
	 * 리플들을 JSON 형식으로 반환한다.
	 * @param p_no
	 * @return
	 * @throws JsonProcessingException
	 */
	public String getJSONReplys(int p_no) throws JsonProcessingException{
		List<ReplyVo> list = dao.getReplys(p_no);
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(list);
	}
	public List<ReplyVo> getReplys(int p_no) {
		List<ReplyVo> list = dao.getReplys(p_no);
		return list;
	}

	public boolean insertReply(ReplyVo r) {
		// TODO Auto-generated method stub
		int insertedCount = dao.insertReply(r);
		if(insertedCount == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean deleteReply(int r_no) {
		// TODO Auto-generated method stub
		int deletedCount = dao.deleteReply(r_no);
		if(deletedCount == 1){
			return true;
		}
		else{
			return false;
		}
	}
}
