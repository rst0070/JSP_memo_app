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
        String tag = request.getPathInfo();
        if(tag == null) tag = "/memo";
        try{
            tag = tag.substring(1);
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

            response.sendRedirect("/tag.jsp");

        }catch(ReadWriteException e){

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
}
