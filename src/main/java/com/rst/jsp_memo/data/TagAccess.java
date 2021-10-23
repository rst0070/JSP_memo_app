package com.rst.jsp_memo.data;
import java.sql.*;
import java.util.*;
import com.rst.jsp_memo.Util;

public class TagAccess implements DataAccess<Tag>{

    private static TagAccess access = null;
    private Connection connection;
    private TagAccess(){
        this.connection = DBConnection.getConnection();
    }

    public static TagAccess getAccess(){
        if(access == null) access = new TagAccess();
        return access;
    }


    @Override
    public boolean isEntityExist(String tagName){
        boolean isExist = true;
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select name from TAG where name = '"+tagName+"' ");

            isExist = rs.next();
            rs.close();
            st.close();
        }catch(SQLException se){
            se.printStackTrace();
            isExist = false;
        }
        return isExist;
    }

        /**
     * returns Tag obj by it's name.
     * @param name - name of tag
     * @return Tag, 존재하지 않거나 오류발생시 null 리턴.
     */
    @Override
    public Tag selectEntity(String name){
        Tag tag = null;
        if(!isEntityExist(name)) return tag;
        
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from TAG where name = '"+name+"' ");

            rs.next();

            tag = new Tag();
            tag.setName(rs.getString("name"));
            
            LinkedList<String> memoList = new LinkedList<String>();
            StringTokenizer memos = new StringTokenizer(rs.getString("memo_list"), "#");
            while(memos.hasMoreTokens()) memoList.add(memos.nextToken());
            tag.setMemoList(memoList);

            rs.close();
            st.close();
        }catch(SQLException se){
            se.printStackTrace();
            tag = null;
        }

        return tag;
    }


    @Override
    public void insertEntity(Tag t){
        if(!t.checkValidation()){System.out.println("cannot insert tag: it's not valid tag"); return;}
        
        String tagName = t.getName();
        String memoListText = Util.tokenListToString(t.getMemoList());

        try{
            String sql = "insert into TAG (name, memo_list) values (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, tagName);
            ps.setString(2, memoListText);

            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * when the entity that has same name as t is exist in database,
     * changes memo_list in database
     */
    @Override
    public void updateEntity(Tag t){
        if(!t.checkValidation()){System.out.println("cannot insert tag: it's not valid tag"); return;}

        String tagName = t.getName();
        String memoListText = Util.tokenListToString(t.getMemoList());
        try{
            String sql = "update TAG "+
            "set memo_list = '?' "+
            "where name = '?'";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, memoListText);
            ps.setString(2, tagName);
            
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * delete entity where t.name == TAG.name
     */
    @Override
    public void deleteEntity(String name){

        try{
            String sql = "delete from TAG where name = '?' ";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

}
