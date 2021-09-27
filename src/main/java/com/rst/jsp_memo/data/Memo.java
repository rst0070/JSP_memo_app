package com.rst.jsp_memo.data;
import java.util.LinkedList;

public class Memo {
	
	private long MEMO_ID;
	private LinkedList<String> tags;
	private String title;
	private String contents;

	private Memo(LinkedList<String> tags, String title, String contents, long memo_id){
		MEMO_ID = memo_id;
		this.tags = tags;
		this.title = title;
		this.contents = contents;
	}

	/**
	 * @tags: 태그 리스트
	 * @title: 메모의 제목
	 * @contents: 메모의 내용
	 * 올바르지 않은 입력을 주었을시 null 반환
	 */
	public static Memo createMemo(LinkedList<String> tags, String title, String contents){
		
		Memo memo = null;

		if(title.length() != 0 && contents.length() != 0){

			long id = DataCenter.getNewMemoId();
			memo = new Memo(tags, title, contents, id);
		}
		
		return memo;
	}

	public long getMemoId(){return this.MEMO_ID;}

	public String getTitle(){return new String(this.title);}

	public String getContents(){return new String(this.contents);}

	public LinkedList<String> getTagList(){
		return (LinkedList<String>)(this.tags).clone();
	}

	public void setMemoId(long id){	this.MEMO_ID = id;}

	public void setContents(String contents){	this.contents = contents;}

	public void setTagList(LinkedList<String> list){	this.tags = list;}
}
