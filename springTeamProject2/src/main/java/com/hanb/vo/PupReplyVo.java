package com.hanb.vo;

import java.sql.Date;

public class PupReplyVo {
	private int r_no;
	private String r_content;
	private String m_id;
	private Date r_regdate;
	private int p_no;
	private String f_name;
	private String choice;
	public PupReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PupReplyVo(int r_no, String r_content, String m_id, Date r_regdate, int p_no, String f_name, String choice) {
		super();
		this.r_no = r_no;
		this.r_content = r_content;
		this.m_id = m_id;
		this.r_regdate = r_regdate;
		this.p_no = p_no;
		this.f_name = f_name;
		this.choice = choice;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public Date getR_regdate() {
		return r_regdate;
	}
	public void setR_regdate(Date r_regdate) {
		this.r_regdate = r_regdate;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
}	
