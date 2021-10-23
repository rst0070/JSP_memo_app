package com.rst.jsp_memo;
import java.util.*;

public class Util {
    public static String tokenListToString(List<String> list){
        String result = "";
        Iterator<String> it = list.iterator();
        while(it.hasNext()) result += '#' + it.next();

        return result;
    }

    public static LinkedList<String> tokenStringToList(String tokened){
        StringTokenizer st = new StringTokenizer(tokened, "#");
        LinkedList<String> list = new LinkedList<String>();
        while(st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
