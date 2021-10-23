package com.rst.jsp_memo.data;
import java.util.Calendar;
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

		if(id == null) return false;
		if(title == null) return false;
		if(content == null) return false;
		if(tagList == null) return false;
		else{
			TagAccess ta = TagAccess.getAccess();
			Iterator<String> it = tagList.iterator();
			while(it.hasNext()){
				if( !ta.isEntityExist( it.next() ) ) return false;
			}
		}	

		return true;
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

	/**
	 * Using present time data, make unique String for primary key of this Entity.
	 */
	public void setId(){
		Calendar cal = Calendar.getInstance();
		String timeId = "";
		timeId += cal.get(cal.MILLISECOND);
		timeId += cal.get(cal.SECOND);
		timeId += cal.get(cal.MINUTE);
		timeId += cal.get(cal.HOUR);
		timeId += cal.get(cal.DATE);
		timeId += cal.get(cal.MONTH);
		timeId += cal.get(cal.YEAR);
		this.id = timeId;
	}

	/**
	 * using get Entity from DB
	 */
	public void setId(String id){
		this.id = id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setContent(String content){	this.content = content;}

	public void setTagList(LinkedList<String> list){	this.tagList = list;}
}
