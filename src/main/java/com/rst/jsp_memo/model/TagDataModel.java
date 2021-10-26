package com.rst.jsp_memo.model;
import com.rst.jsp_memo.data.Memo;
import com.rst.jsp_memo.data.MemoAccess;
import com.rst.jsp_memo.data.Tag;
import com.rst.jsp_memo.data.TagAccess;

import java.util.*;

public class TagDataModel {
    private Tag tag;
    private LinkedList<Memo> memoList = new LinkedList<Memo>();
    private LinkedList<Tag> allTagList = new LinkedList<Tag>();

    public TagDataModel(Tag tag){
        this.tag = tag;
        MemoAccess access = MemoAccess.getAccess();
        Iterator<String> it = tag.getMemoList().iterator();
        while(it.hasNext()){
            Memo m = access.selectEntity(it.next());
            if(m != null)
                memoList.add(m);
        }

        TagAccess ta = TagAccess.getAccess();
        allTagList = ta.selectAll();
    }

    /**
     * @param tagName
     */

    public String getTagName(){ return tag.getName();}

    public LinkedList<Memo> getMemoList(){
        return (LinkedList<Memo>)memoList.clone();
    }

    public LinkedList<Tag> getAllTags(){
        return (LinkedList<Tag>)allTagList.clone();
    }
}
