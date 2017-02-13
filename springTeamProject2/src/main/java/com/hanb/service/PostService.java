package com.hanb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanb.dao.PostDao;
import com.hanb.enums.Board;
import com.hanb.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao dao;
	
	public String getPageStr(Board board){
		String str = "";
		int startPage = (PostDao.pageNum - 1) / PostDao.pageGroup * PostDao.pageGroup + 1;
		int endPage = startPage + PostDao.pageGroup - 1;
		if(endPage > PostDao.totalPage){
			endPage = PostDao.totalPage;
		}
		
		if(startPage > 1){
			str += "<a href=list"+board+".do?pageNum="+(startPage - 1)+">����</a>";
		}
		for(int curPage = startPage; curPage <= endPage; curPage++){
			str += "&nbsp<a href=list"+board+".do?pageNum="+curPage+">"+curPage+"</a>&nbsp";
		}
		if(endPage < PostDao.totalPage){
			str += "<a href=list"+board+".do?pageNum="+(endPage + 1)+">����</a>";
		}
		return str;
	}
	
	public List<Map<String, String>> getListPost(Board board, String pageNum){
		int pageNumber = 0;
		if(pageNum == null){
			pageNumber = 1;
		}
		else{
			pageNumber = Integer.parseInt(pageNum);
		}
		return dao.getListPost(board.getBoardNum(),pageNumber,board);
	}
	
	/** �Խñ��� ����Ѵ�.
	 * @param p ��ϵ� �Խñ� ������
	 * @param board ��ϵ� �Խ��� ��ȣ
	 * @return ��ϵ� �Խñ� ��ȣ
	 */
	public int insertPost(PostVo p,Board board){
		p.setP_content(p.getP_content().replace("src=\"resources/upload/tmp","src=\"resources/upload/"+FileService.getSavePathName()));
		p.setB_no(board.getBoardNum());
		return dao.insertPost(p);
	}
	
	public int deletePost(int p_no){
		return dao.deletePost(p_no);
	}
	
	public int updatePost(PostVo p, int p_no){
		p.setP_content(p.getP_content().replace("src=\"resources/upload/tmp","src=\"resources/upload/"+FileService.getSavePathName()));
		p.setP_no(p_no);
		return dao.updatePost(p);
	}
	
	/** Ư�� �Խñ��� ��������� �����´�, addHit�� true���� �ָ� ��ȸ���� 1 ������Ų��.
	 * @param p_no
	 * @param addHit
	 * @return
	 */
	public PostVo detailPost(int p_no,boolean addHit){
		if(addHit) {
			dao.updatePostHit(p_no);
		}
		return dao.detailPost(p_no);
	}
}
