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

        JSONObject data = readJsonObject(req.getReader());
        String action = (String)data.get("action");
        System.out.println(action);       
        
        switch(action){
            case "create":
            create(data); break;
            case "modify":
            modify(data); break;
            case "delete":
            delete(data); break;
            default: break;
        }

        res.setStatus(200);
    }

    private JSONObject readJsonObject(BufferedReader br) throws IOException{
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null)
            sb.append(str);
        System.out.println(sb.toString());
        JSONObject jobj = new JSONObject(sb.toString());
        return jobj;
    }

    private MemoAccess access = MemoAccess.getAccess();

    private void create(JSONObject data){
        String title = (String)data.get("title");
        String content = (String)data.get("content");
        Iterator tagIt = data.getJSONArray("tagList").iterator();
        
        Memo m = memoObj(title, content, tagIt);
        m.setId();
        access.insertEntity(m);
    }

    private void modify(JSONObject data){
        String id = (String)data.get("memoID");
        String title = (String)data.get("title");
        String content = (String)data.get("content");
        Iterator tagIt = data.getJSONArray("tagList").iterator();
        
        Memo m = memoObj(title, content, tagIt);
        m.setId(id);
        access.updateEntity(m);
    }

    private void delete(JSONObject data){
        String id = (String)data.get("memoID");
        access.deleteEntity(id);
    }

    private Memo memoObj(String title, String content, Iterator tagIterator){
        LinkedList<String> tagList = new LinkedList<String>();
        while(tagIterator.hasNext()) tagList.add((String)tagIterator.next());

        Memo m = new Memo();
        m.setTitle(title);
        m.setContent(content);
        m.setTagList(tagList);
        return m;
    }
}
