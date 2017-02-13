package com.hanb.enums;

public enum Member {
	UNCERTIFICATED_MEMBER(10),MEMBER(20),DIRECTOR(30),ADMIN(40);
	int grade;
	Member(int grade){
		this.grade = grade;
	}
	public int getNumber(){
		return grade;
	}
	public String getNumberAsString(){
		return String.valueOf(grade);
	}
}
