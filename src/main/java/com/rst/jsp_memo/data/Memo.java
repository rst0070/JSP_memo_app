package com.rst.jsp_memo.data;

public class Memo {
	
	private final long MEMO_ID;
	
	private Memo(String title, String contents, long memo_id){
		MEMO_ID = memo_id;
	}

	/**
	 * @tags: 태그 리스트들 띄어쓰기로 구분(모든 메모는 기본적으로 'memo')
	 * @title: 메모의 제목
	 * @contents: 메모의 내용
	 *
	 */
	public static Memo createMemo(String tags, String title, String contents){
		long id = DataCenter.getNewMemoId();
		
		Memo memo = null;
		if(title.length() != 0 && contents.length() != 0) memo = new Memo(title, contents, id);
		
		return memo;
	}
}
