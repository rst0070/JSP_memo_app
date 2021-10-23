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
     * @param id - id of memo looking for.
     * @return true: The memo exists, false: The memo does not exist
     */
    @Override
    public boolean isEntityExist(String id){
        boolean exist = true;
        try{
            String sql = "select id from MEMO where id = '?' ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            exist = rs.next();
        }catch(SQLException se){
            se.printStackTrace();
            exist = false;
        }
        return exist;
    }

    /**
     * 
     * @param id : id of find.
     * @return if wrong id or errors, returns null.
     */
    @Override
    public Memo selectEntity(String id){
        Memo memo = null;
        if(!isEntityExist(id)) return memo;
        
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from MEMO where id = '"+id+"'");

            rs.next();

            memo = new Memo();
            memo.setId(rs.getString("id"));
            memo.setId(rs.getString("title"));

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
    public void deleteEntity(String id){

        String sql =
        "delete from MEMO where id = '?' ";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
