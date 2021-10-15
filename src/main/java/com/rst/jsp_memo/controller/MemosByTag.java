package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.rst.jsp_memo.data.*;
import com.rst.jsp_memo.model.*;

public class MemosByTag extends HttpServlet{
    static final long SerialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String sessionId = request.getSession().getId();

        //사용자가 요청한 태그명을 가져온다. 오류시 기본태그명사용
        String tag = request.getPathInfo();
        if(tag == null) tag = "/memo";
        tag = tag.substring(1);

        try{
            saveModelForTagPage(tag, sessionId);
            response.sendRedirect("/tag.jsp");

        }catch(ReadWriteException e){
            
            BufferedWriter bw = new BufferedWriter(response.getWriter());
            bw.write(e.getMessage());
        }
    }

    /**
     * 지정된 태그명으로 모델을 만들어 sessionId를 Key로하여 model.repository에 저장한다.
     * @param tag - 태그명
     * @param sessionId - session id of request
     */
    private void saveModelForTagPage(String tag, String sessionId) throws ReadWriteException{

        LinkedList<String> tags = DataCenter.getAllTags();
        Iterator<String> it = tags.iterator();

        boolean thereIsTag = false;
        while(it.hasNext()){
            if( it.next().equals(tag) ){thereIsTag = true; break;}
        }

        LinkedList<Memo> memos = null;
        if(thereIsTag){
            memos = DataCenter.getMemosByTag( tag );
        }else{ memos = DataCenter.getMemosByTag( "memo" );}

        TagDataModel model = new TagDataModel(tag, memos);
        Repository.put(sessionId, model);

    }

    /**
     * 메모를 수정하거나 새로만드는 작업을 진행한다.
     * 
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        /**
         * path는 /tag/***
         * 같은 형식으로 들어오게된다.
         */
        //String path = req.getRequestURI().toString();
        /*
        Enumeration<String> tags = req.getParameterNames();
        while( tags.hasMoreElements() ){
            System.out.println(tags.nextElement());
        }*/
        Long memoId = Long.parseLong(req.getParameter("memoId"));
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        String[] tagArr = req.getParameterValues("tags[]");
        LinkedList<String> tags = new LinkedList<String>();
        for( String t : tagArr ){
            tags.add(t);
        }

        try{
            DataCenter.createMemo(memoId, tags, title, contents);
        }catch(ReadWriteException e){
            e.printStackTrace();
        }
        
    }
}
