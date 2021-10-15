package com.rst.jsp_memo.model;

import java.util.*;
import com.rst.jsp_memo.data.DataCenter;
import com.rst.jsp_memo.data.ReadWriteException;
public class Repository {
    private static HashMap<String, Object> map = new HashMap<String, Object>();

    public static void put(String sessionId, Object model){
        map.put(sessionId, model);
    }

    public static Object get(String sessionId){
        return map.get(sessionId);
    }

    public static LinkedList<String> getAllTags() throws ReadWriteException{
        LinkedList<String> list = DataCenter.getAllTags();
        return list;
    }
}
