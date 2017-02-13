package com.hanb.vo;


public class SearchVo {
	private String keyField;
	private String keyWord;
	private String orderField;
	private String startDate;
	private String endDate;
	private int b_no;
	private int start;
	private int end;
	
	@Override
	public String toString() {
		return "SearchVo [keyField=" + keyField + ", keyWord=" + keyWord + ", orderField=" + orderField + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	public SearchVo(String keyField, String keyWord, String orderField) {
		super();
		this.keyField = keyField;
		this.keyWord = keyWord;
		this.orderField = orderField;
	}
	public SearchVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getKeyField() {
		return keyField;
	}
	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
}
