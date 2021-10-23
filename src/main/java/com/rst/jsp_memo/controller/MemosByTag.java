package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.rst.jsp_memo.data.*;
import com.rst.jsp_memo.model.*;

public class MemosByTag extends HttpServlet{
    static final long SerialVersionUID = 1L;
    private TagAccess tagAccess = TagAccess.getAccess();
    private MemoAccess memoAccess = MemoAccess.getAccess();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();

        session.setAttribute("visitedController", "true");
        String sessionId = session.getId();

        //사용자가 요청한 태그명을 가져온다. 오류시 기본태그명사용
        String tag = request.getPathInfo();
        if(tag == null) tag = "/memo";
        tag = tag.substring(1);

        saveModelForTagPage(tag, sessionId);
        response.sendRedirect("/tag.jsp");

        
    }

    /**
     * 지정된 태그명으로 모델을 만들어 sessionId를 Key로하여 model.repository에 저장한다.
     * @param tag - 태그명
     * @param sessionId - session id of request
     */
    private void saveModelForTagPage(String tag, String sessionId){

        Tag tagObj = tagAccess.selectEntity(tag);
        Iterator<String> it = tagObj.getMemoList().iterator();
        LinkedList<Memo> memoList = new LinkedList<Memo>();

        while(it.hasNext())
            memoList.add( memoAccess.selectEntity( it.next() ) );

        TagDataModel model = new TagDataModel(tag, memoList);
        Repository.put(sessionId, model);

    }

    /**
     * 메모를 수정하거나 새로만드는 작업을 진행한다.
     * 
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
    }

}
