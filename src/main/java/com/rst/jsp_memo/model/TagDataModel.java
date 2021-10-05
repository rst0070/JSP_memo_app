package com.rst.jsp_memo.model;
import com.rst.jsp_memo.data.Memo;
import java.util.*;

public class TagDataModel {
    private String tag;
    private LinkedList<Memo> memos;
    public TagDataModel(String tag, LinkedList<Memo> memos){
        this.tag = tag;
        this.memos = memos;
    }

    /**
     * set을 사용하게 될지는 의문이다.
     * 왜냐하면 view는 그저 보여주는 역할을 할뿐이기 때문이다.
     * 수정이 발생할경우  post요청을 통해 컨트롤러가 해당작업을 진행하고 
     * view를 다시 보내줄 것 이다.
     * 
     * @param tagName
     */

    public String getTagName(){ return new String( tag );}

    public LinkedList<Memo> getMemoList(){
        return (LinkedList<Memo>)memos.clone();
    }
}
