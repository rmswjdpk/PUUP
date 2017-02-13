package com.hanb.vo;

import java.sql.Timestamp;

public class BoardVo {
	 private int b_no;
	 private int b_name;
	 private Timestamp b_regdate;
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public int getB_name() {
		return b_name;
	}
	public void setB_name(int b_name) {
		this.b_name = b_name;
	}
	public Timestamp getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(Timestamp b_regdate) {
		this.b_regdate = b_regdate;
	}
	 
	 
}
