package com.rst.jsp_memo.data;
import java.util.Iterator;
import java.util.LinkedList;

public class Memo implements Entity{
	
	private String id = null;
	private LinkedList<String> tagList = null;
	private String title = null;
	private String content = null;

	/**
	 * check this memo is valid.
	 * 1. all tags are exist
	 * 2. id, title, content, tag_list are not null value.
	 * 3. 
	 */
	@Override
	public boolean checkValidation(){
		boolean valid = true;

		if(tagList == null) valid = false;
		else{
			TagAccess ta = TagAccess.getAccess();
			Iterator<String> it = tagList.iterator();
			while(it.hasNext()){
				valid = ta.isEntityExist(it.next());
			}
		}

		if(id == null) valid = false;
		if(title == null) valid = false;
		if(content == null) valid = false;
		

		return valid;
	}

	public String getId(){
		if(checkValidation()) return new String(this.id);
		return null;
	}

	public String getTitle(){
		if(checkValidation()) return new String(this.title);
		return null;
	}

	public String getContent(){
		if(checkValidation()) return new String(this.content);
		return null;
	}

	public LinkedList<String> getTagList(){
		if(checkValidation()) return (LinkedList<String>)(this.tagList).clone();
		return null;
	}

	public void setId(String id){ this.id = id;}

	public void setContent(String content){	this.content = content;}

	public void setTagList(LinkedList<String> list){	this.tagList = list;}
}
