package com.rst.jsp_memo.data;

public class Memo {
	
	private final long MEMO_ID;
	
	private Memo(String title, String contents, long memo_id){
		MEMO_ID = memo_id;
	}
	public static Memo createMemo(String title, String contents){
		long id = DataCenter.getNewMemoId();
		
		Memo memo = null;
		if(title.length() != 0 && contents.length() != 0) memo = new Memo(title, contents, id);
		
		return memo;
	}
}
