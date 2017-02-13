package com.hanb.vo;

import java.sql.Timestamp;

public class FileVo {
	private int f_name;
	private String f_realname;
	private String f_savepath;
	private Timestamp f_regdate;
	private int p_no;
	
	@Override
	public String toString() {
		return "FileVo [f_name=" + f_name + ", f_realname=" + f_realname + ", f_savepath=" + f_savepath + ", f_regdate="
				+ f_regdate + ", p_no=" + p_no + "]";
	}
	public FileVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileVo(int f_name, String f_realname, String f_savepath, int p_no) {
		super();
		this.f_name = f_name;
		this.f_realname = f_realname;
		this.f_savepath = f_savepath;
		this.p_no = p_no;
	}
	
	public FileVo(int f_name, String f_savepath, int p_no) {
		super();
		this.f_name = f_name;
		this.f_savepath = f_savepath;
		this.p_no = p_no;
	}
	
	public FileVo(int f_name, String f_savepath) {
		super();
		this.f_name = f_name;
		this.f_savepath = f_savepath;
	}

	public int getF_name() {
		return f_name;
	}
	public void setF_name(int f_name) {
		this.f_name = f_name;
	}
	public String getF_realname() {
		return f_realname;
	}
	public void setF_realname(String f_realname) {
		this.f_realname = f_realname;
	}
	public Timestamp getF_regdate() {
		return f_regdate;
	}
	public void setF_regdate(Timestamp f_regdate) {
		this.f_regdate = f_regdate;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getF_savepath() {
		return f_savepath;
	}
	public void setF_savepath(String f_savepath) {
		this.f_savepath = f_savepath;
	}
	
	
}
