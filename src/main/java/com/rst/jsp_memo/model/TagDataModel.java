package com.rst.jsp_memo.model;
import com.rst.jsp_memo.data.Memo;
import com.rst.jsp_memo.data.MemoAccess;
import com.rst.jsp_memo.data.Tag;

import java.util.*;

public class TagDataModel {
    private String tag;
    private LinkedList<Memo> memoList = new LinkedList<Memo>();
    public TagDataModel(Tag tag){
        this.tag = tag.getName();
        MemoAccess access = MemoAccess.getAccess();
        Iterator<String> it = tag.getMemoList().iterator();
        while(it.hasNext()){
            Memo m = access.selectEntity(it.next());
            if(m != null)
                memoList.add(m);
        }
    }

    /**
     * @param tagName
     */

    public String getTagName(){ return new String( tag );}

    public LinkedList<Memo> getMemoList(){
        return (LinkedList<Memo>)memoList.clone();
    }
}
