package com.rst.jsp_memo.data;
import java.util.LinkedList;

public class Memo implements Entity{
	
	private String id = null;
	private LinkedList<String> tagList = null;
	private String title = null;
	private String content = null;

	@Override
	public boolean checkValidation(){
		boolean result = true;
		if(id == null) result = false;
		if(title == null) result = false;
		if(content == null) result = false;
		if(tagList == null) result = false;

		return result;
	}
	public String getId(){return this.id;}

	public String getTitle(){return new String(this.title);}

	public String getContent(){return new String(this.content);}

	public LinkedList<String> getTagList(){
		return (LinkedList<String>)(this.tagList).clone();
	}

	public void setId(String id){ this.id = id;}

	public void setContent(String content){	this.content = content;}

	public void setTagList(LinkedList<String> list){	this.tagList = list;}
}
