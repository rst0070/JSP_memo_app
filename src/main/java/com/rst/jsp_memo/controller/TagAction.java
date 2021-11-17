package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import com.rst.jsp_memo.data.*;
import java.io.*;
import org.json.*;
import java.util.*;
public class TagAction extends HttpServlet{
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
            case "delete":
            delete(data); break;
            default: break;
        }

        res.setStatus(200);
    }

    private JSONObject readJsonObject(BufferedReader br) throws IOException{
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null) sb.append(str);
        JSONObject json = new JSONObject(sb.toString());
        return json;
    }

    private TagAccess access = TagAccess.getAccess();

    private void create(JSONObject data){
        String tagName = (String)data.get("name");
        Tag t = new Tag();
        t.setName(tagName);
        t.setMemoList(new LinkedList<String>());
        access.insertEntity(t);
    }

    private void delete(JSONObject data){
        String tagName = (String)data.get("name");
        
        access.deleteEntity(tagName);
    }
}