package com.rst.jsp_memo.model;

import java.util.*;

public class Repository {
    private static HashMap<String, Object> map = new HashMap<String, Object>();

    public static void put(String sessionId, Object model){
        map.put(sessionId, model);
    }

    public static Object get(String sessionId){
        return map.get(sessionId);
    }
}
