package com.hanb.vo;

import java.sql.Date;

public class PupRequestVo {
	private int p_no;
	private String p_title;
	private String pu_category;
	private Date p_regdate;
	private int p_hit;
	private String p_content;
	private String m_id;
	private int b_no;
	private String sample_period;
	private String product_period;
	private int expection_price;
	private String f_name;
	private String f_realname;
	public PupRequestVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PupRequestVo(int p_no, String p_title, String pu_category, Date p_regdate, int p_hit, String p_content,
			String m_id, int b_no, String sample_period, String product_period, int expection_price,
			String f_name, String f_realname) {
		super();
		this.p_no = p_no;
		this.p_title = p_title;
		this.pu_category = pu_category;
		this.p_regdate = p_regdate;
		this.p_hit = p_hit;
		this.p_content = p_content;
		this.m_id = m_id;
		this.b_no = b_no;
		this.sample_period = sample_period;
		this.product_period = product_period;
		this.expection_price = expection_price;
		this.f_name = f_name;
		this.f_realname = f_realname;
	}
	

	public String getPu_category() {
		return pu_category;
	}

	public void setPu_category(String pu_category) {
		this.pu_category = pu_category;
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
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getSample_period() {
		return sample_period;
	}
	public void setSample_period(String sample_period) {
		this.sample_period = sample_period;
	}
	public String getProduct_period() {
		return product_period;
	}
	public void setProduct_period(String product_period) {
		this.product_period = product_period;
	}
	public int getExpection_price() {
		return expection_price;
	}
	public void setExpection_price(int expection_price) {
		this.expection_price = expection_price;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_realname() {
		return f_realname;
	}
	public void setF_realname(String f_realname) {
		this.f_realname = f_realname;
	}
	
	
	
}
