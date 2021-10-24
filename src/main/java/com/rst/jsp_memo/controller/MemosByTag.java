package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.rst.jsp_memo.Util;
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

        //사용자가 요청한 태그명을 가져온다. 오류시 모든메모 불러옴
        String tagName = request.getPathInfo();
        tagName = tagName.substring(1);// /memo 같은 형식으로 옴.

        Tag tag = tagAccess.selectEntity(tagName);

        if(tag == null){
            tag = new Tag();
            Iterator<Memo> allMemo = memoAccess.selectAll().iterator();
            String str = "";
            while(allMemo.hasNext()) str += '#'+allMemo.next().getId();
            tag.setMemoList(Util.tokenStringToList(str));
            tag.setName("");
        }
        
        TagDataModel model = new TagDataModel(tag);
        Repository.put(sessionId, model);

        response.sendRedirect("/tag.jsp");

        
    }

   

    /**
     * 메모를 수정하거나 새로만드는 작업을 진행한다.
     * 
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
    }

}
