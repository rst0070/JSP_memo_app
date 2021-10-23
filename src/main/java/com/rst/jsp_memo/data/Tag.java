package com.rst.jsp_memo.data;
import java.util.LinkedList;

public class Tag implements Entity{
    private String name = null;
    private LinkedList<String> memoList = null;

    /**
     * 해당 태그의 유효성 검사.
     */
    @Override
    public boolean checkValidation(){
        boolean valid = true;

        if(name == null) valid = false;
        if(memoList == null) valid = false;
        
        return valid;
    }

    public void setName(String name){ this.name = name; };

    public void setMemoList(LinkedList<String> list){ this.memoList = list; };

    public String getName(){
        if(checkValidation()) return new String(name);
        return null;
    }

    public LinkedList<String> getMemoList(){
        if(checkValidation()) return (LinkedList<String>)memoList.clone();
        return null;
    }
}