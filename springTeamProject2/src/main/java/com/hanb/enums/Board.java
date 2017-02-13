package com.hanb.enums;

public enum Board {
	Notice(100),QandA(110),Free(120),Share(130),PUP(140),DRequest(150);
	
	final int boardNum;
	Board(int boardNum){
		this.boardNum = boardNum;
	}
	public int getBoardNum(){
		return boardNum;
	}
}