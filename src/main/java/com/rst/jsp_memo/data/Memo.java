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
		if(this.id == null) return null;
		return new String(this.id);
	}

	public String getTitle(){
		if(this.title == null) return null;
		return new String(this.title);
	}

	public String getContent(){
		if(this.content == null) return null;
		return new String(this.content);
	}

	public LinkedList<String> getTagList(){
		if(this.tagList == null) return null;
		return (LinkedList<String>)(this.tagList).clone();
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
		if(id == null) this.id = null;
		else this.id = new String(id);
	}

	public void setTitle(String title){
		if(title == null) this.title = null;
		else this.title = new String(title);
	}

	public void setContent(String content){
		if(content == null) this.content = null;
		else this.content = new String(content);}

	public void setTagList(LinkedList<String> list){
		if(list == null) this.tagList = null;
		else this.tagList = (LinkedList<String>)list.clone();
	}

	@Override
	public boolean equals(Object memo2){
		if(!(memo2 instanceof Memo)) return false;
		Memo m2 = (Memo)memo2;
		if(!m2.checkValidation() || !checkValidation()) return false;

		if(!m2.getId().equals(this.id)) return false;
		if(!m2.getTitle().equals(this.title)) return false;
		if(!m2.getTagList().equals(this.tagList)) return false;
		if(!m2.getContent().equals(this.content)) return false;
		
		return true;
	}

}
