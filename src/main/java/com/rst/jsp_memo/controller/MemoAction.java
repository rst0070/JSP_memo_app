package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MemoAction extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //wrong request.
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }

    static class Data{
        String id = null;
        String title = null;
        String content = null;
        LinkedList<String> tagList = null;
    }

    /**
     * 
     * @param d contains title, content, tagList and id will defined by Memo obj
     * @return  success: true not: false
     */
    private boolean createMemo(Data d){
        boolean result = true;


        return result;
    }
    /**
     * 
     * @param d contains id, title, content and tagList
     * @return  success: true not: false
     */
    private boolean modifyMemo(Data d){
        boolean result = true;


        return result;
    }
    /**
     * 
     * @param d only contains id of memo to delete
     * @return  success: true not: false
     */
    private boolean deleteMemo(Data d){
        boolean result = true;


        return result;
    }
}
