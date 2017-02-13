package com.hanb.vo;

public class LoginMemberVo {
	private String m_id;
	private int g_num;
	private String g_title;
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	@Override
	public String toString() {
		return "LoginMemberVo [m_id=" + m_id + ", g_num=" + g_num + ", g_title=" + g_title + "]";
	}
	
	
}
