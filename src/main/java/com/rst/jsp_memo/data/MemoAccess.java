package com.rst.jsp_memo.data;
import java.util.*;

import com.rst.jsp_memo.Util;

import java.sql.*;

public class MemoAccess implements DataAccess<Memo>{

    private static MemoAccess access = null;
    private Connection connection;
    private MemoAccess(){
        this.connection = DBConnection.getConnection();
    }

    public static MemoAccess getAccess(){
        if(access == null) access = new MemoAccess();
        return access;
    }

    /**
     * 
     * @param id : id of find.
     * @return if wrong id or errors, returns null.
     */
    public Memo getById(String id){
        Memo memo = null;
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from MEMO where id = '"+id+"'");

            rs.next();

            memo = new Memo();
            memo.setId(rs.getString("id"));
            
            LinkedList<String> tagList = Util.tokenStringToList(rs.getString("tag_list"));
            memo.setTagList(tagList);

            memo.setContent(rs.getString("content"));

            rs.close();
            st.close();
        }catch(SQLException se){
            se.printStackTrace();
            memo = null;
        }

        return memo;
    }
    @Override
    public void insertEntity(Memo m){
        if(!m.checkValidation()) return;
        String sql =
        "insert into MEMO (id, title, tag_list, content) values ('?', '?', '?', '?')";

        String tagList = Util.tokenListToString(m.getTagList());
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getId());
            ps.setString(2, m.getTitle());
            ps.setString(3, tagList);
            ps.setString(4, m.getContent());
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    @Override
    public void updateEntity(Memo m){
        if(!m.checkValidation()) return;
        String sql =
        "update MEMO set title = '?', tag_list = '?', content = '?' where id = '?' ";

        String tagList = Util.tokenListToString(m.getTagList());
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(4, m.getId());
            ps.setString(1, m.getTitle());
            ps.setString(2, tagList);
            ps.setString(3, m.getContent());
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Memo m){
        if(!m.checkValidation()) return;
        String sql =
        "delete from MEMO where id = '?' ";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getId());
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
