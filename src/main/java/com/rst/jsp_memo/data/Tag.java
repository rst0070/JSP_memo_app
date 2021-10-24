package com.rst.jsp_memo.data;
import java.util.LinkedList;

public class Tag implements Entity{
    private String name = null;
    private LinkedList<String> memoList = null;

    /**
     * check this tag is valid
     * 1. all thing is not null value
     */
    @Override
    public boolean checkValidation(){
        boolean valid = true;

        if(name == null) valid = false;
        if(memoList == null) valid = false;
        
        return valid;
    }

    public void setName(String name){ 
        if(name == null) this.name = null;
        else this.name = new String(name);
    };

    public void setMemoList(LinkedList<String> list){ 
        if(list == null) this.memoList = null;
        else this.memoList = (LinkedList<String>)list.clone(); 
    }

    public String getName(){
        if(name == null) return null;
        return new String(name);        
    }

    public LinkedList<String> getMemoList(){
        if(memoList == null) return null;
        return (LinkedList<String>)memoList.clone();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Tag)) return false;
        Tag t = (Tag)obj;
        if(!t.checkValidation() || !checkValidation()) return false;
        if(!t.getMemoList().equals(memoList)) return false;
        if(!t.getName().equals(name)) return false;
        return true;
    }
}
