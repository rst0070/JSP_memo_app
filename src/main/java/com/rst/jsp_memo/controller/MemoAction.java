package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.rst.jsp_memo.data.*;
import org.json.*;

public class MemoAction extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //wrong request.
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{

        BufferedWriter bw = new BufferedWriter(res.getWriter());
        // /memo/create 같은 형식으로 옴.
        String path = req.getRequestURI().toString();
        String action = path.substring(6);
        if(action == null) return;

        processData(action, req);
        bw.write("hihihi");
        bw.flush();
        bw.close();
    }


    private LinkedList<String> jsonArrayToStringList(String jsonArr){
        LinkedList<String> result = new LinkedList<String>();
        JSONArray array = new JSONArray(jsonarr);
        Iterator<Object> it = array.iterator();
        while(it.hasNext()){
            result.add((String)it.next());
        }
        return result;
    }

    /**
     * classify action, and process case by case
     * @param action - type of action : "create", "delete", "modify"
     * @param req - request from client.
     */
    private boolean processData(String action, HttpServletRequest req){
        Data data = new Data();
        
        switch(action){
            case "delete":
            data.id = req.getParameter("memoID");
            return deleteMemo(data);

            case "modify":
            data.id = req.getParameter("memoID");
            data.title = req.getParameter("title");
            data.content = req.getParameter("content");
            data.tagList = jsonArrayToStringList(req.getParameter("tagList"));
            return modifyMemo(data);

            case "create":
            data.title = req.getParameter("title");
            data.content = req.getParameter("content");
            data.tagList = jsonArrayToStringList(req.getParameter("tagList"));
            return createMemo(data);

            default: return false;
        }
    }

    static class Data{
        String id = null;
        String title = null;
        String content = null;
        LinkedList<String> tagList = null;
    }

    private MemoAccess access = MemoAccess.getAccess();
    /**
     * 
     * @param d contains title, content, tagList. Id will defined by Memo obj
     * @return  success: true not: false
     */
    private boolean createMemo(Data d){
        boolean result = true;

        Memo memo = new Memo();
        memo.setId();
        memo.setTitle(d.title);
        memo.setContent(d.content);
        memo.setTagList(d.tagList);

        access.insertEntity(memo);
        return result;
    }
    /**
     * 
     * @param d contains id, title, content and tagList
     * @return  success: true not: false
     */
    private boolean modifyMemo(Data d){
        boolean result = true;

        Memo memo = new Memo();
        memo.setId(d.id);
        memo.setTitle(d.title);
        memo.setContent(d.content);
        memo.setTagList(d.tagList);

        access.updateEntity(memo);
        return result;
    }
    /**
     * 
     * @param d only contains id of memo to delete
     * @return  success: true not: false
     */
    private boolean deleteMemo(Data d){
        boolean result = true;

        access.deleteEntity(d.id);
        return result;
    }
}
