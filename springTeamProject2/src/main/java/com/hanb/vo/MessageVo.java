package com.hanb.vo;

import java.util.Date;

public class MessageVo {
	private String _id;
	private String toID;
	private String fromID;
	private String title;
	private String content;
	private Date saved_at;
	public MessageVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageVo(String _id, String toID, String fromID, String title, String content, Date saved_at) {
		super();
		this._id = _id;
		this.toID = toID;
		this.fromID = fromID;
		this.title = title;
		this.content = content;
		this.saved_at = saved_at;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSaved_at() {
		return saved_at;
	}
	public void setSaved_at(Date saved_at) {
		this.saved_at = saved_at;
	}
	
	
}
