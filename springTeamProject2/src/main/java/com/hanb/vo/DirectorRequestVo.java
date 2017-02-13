package com.hanb.vo;

import java.sql.Date;

public class DirectorRequestVo {
	
	private int p_no;
	private String p_title;
	private String dir_category;
	private String p_content;
	private Date p_regdate;
	private int p_hit;
	private int b_no;
	private String m_id;
	private String f_name;
	public DirectorRequestVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DirectorRequestVo(int p_no, String p_title, String dir_category, String p_content, Date p_regdate, int p_hit,
			 int b_no, String m_id, String f_name) {
		super();
		this.p_no = p_no;
		this.p_title = p_title;
		this.dir_category = dir_category;
		this.p_content = p_content;
		this.p_regdate = p_regdate;
		this.p_hit = p_hit;
		this.b_no = b_no;
		this.m_id = m_id;
		this.f_name = f_name;
	}

	public String getDir_category() {
		return dir_category;
	}

	public void setDir_category(String dir_category) {
		this.dir_category = dir_category;
	}

	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public Date getP_regdate() {
		return p_regdate;
	}
	public void setP_regdate(Date p_regdate) {
		this.p_regdate = p_regdate;
	}
	public int getP_hit() {
		return p_hit;
	}
	public void setP_hit(int p_hit) {
		this.p_hit = p_hit;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
}
