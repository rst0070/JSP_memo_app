package com.rst.jsp_memo.data;
import java.util.*;

import com.rst.jsp_memo.Util;

import java.sql.*;

public class MemoAccess implements DataAccess<Memo>{

    private static MemoAccess access = null;
    private TagAccess accessTag = null;
    private Connection connection;
    private MemoAccess(){
        this.connection = DBConnection.getConnection();
        this.accessTag = TagAccess.getAccess();
    }

    public static MemoAccess getAccess(){
        if(access == null) access = new MemoAccess();
        return access;
    }

    public LinkedList<Memo> selectAll(){
        LinkedList<Memo> list = new LinkedList<Memo>();
        String sql = "select * from MEMO";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Memo m = new Memo();
                m.setId(rs.getString("id"));
                m.setTitle(rs.getString("title"));
                m.setTagList(Util.tokenStringToList(rs.getString("tag_list")));
                m.setContent(rs.getString("content"));
                list.add(m);
            }

            rs.close();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return list;
    }

    /**
     * @param id - id of memo looking for.
     * @return true: The memo exists, false: The memo does not exist
     */
    @Override
    public boolean isEntityExist(String id){
        boolean exist = true;
        try{
            String sql = "select id from MEMO where id = ? ";
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
        
        String sql = "select * from MEMO where id = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            memo = new Memo();
            memo.setId(rs.getString("id"));
            memo.setTitle(rs.getString("title"));

            LinkedList<String> tagList = Util.tokenStringToList(rs.getString("tag_list"));
            memo.setTagList(tagList);

            memo.setContent(rs.getString("content"));

            rs.close();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
            memo = null;
        }

        return memo;
    }

    /**
     * check memo that has same id already exist. if true, end method.<br/>
     * not only insert Memo, but also have to change TAG.memo_list
     */
    @Override
    public void insertEntity(Memo m){
        if(!m.checkValidation()) return;
        if(isEntityExist(m.getId())) return;

        String sql =
        "insert into MEMO (id, title, tag_list, content) values (?, ?, ?, ?)";

        String tagList = Util.tokenListToString(m.getTagList());
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getId());
            ps.setString(2, m.getTitle());
            ps.setString(3, tagList);
            ps.setString(4, m.getContent());
            ps.execute();
            ps.close();

            //modify Tags after memo uploaded to db.
            Iterator<String> tagNames = m.getTagList().iterator();

            while(tagNames.hasNext()){
                String tagName = tagNames.next();
                Tag tag = accessTag.selectEntity(tagName);
                if(tag == null) continue;

                LinkedList<String> memoList = tag.getMemoList();
                memoList.add(m.getId());
                tag.setMemoList(memoList);
                accessTag.updateEntity(tag);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    /**
     * check memo that has same id isn't exist. if true, end method.<br/>
     * how to: remove prev memo from all tags, and add updated memo.<br/>
     * not only update Memo, but also have to change TAG.memo_list
     */
    @Override
    public void updateEntity(Memo m){
        if(!m.checkValidation()) return;
        if(!isEntityExist(m.getId())) return;

        Memo prevMemo = selectEntity(m.getId());
        removeFromTags(prevMemo);

        String sql =
        "update MEMO set title = ?, tag_list = ?, content = ? where id = ? ";

        String tagList = Util.tokenListToString(m.getTagList());
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(4, m.getId());
            ps.setString(1, m.getTitle());
            ps.setString(2, tagList);
            ps.setString(3, m.getContent());
            ps.execute();
            ps.close();
            addToTags(m);

        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * not only delete memo, but also change TAG.memo_list
     */
    @Override
    public void deleteEntity(String id){
        String sql =
        "delete from MEMO where id = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            ps.close();

            //remove from TAG.memo_list
            Memo m = selectEntity(id);
            removeFromTags(m);

        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * add to Tags by update TAG.memo_list to including `m.id`<br/>
     * used by updateEntity, insertEntity
     */
    private void addToTags(Memo m){
        if(m == null) return;
        Iterator<String> tagNames = m.getTagList().iterator();

        while(tagNames.hasNext()){
            String tagName = tagNames.next();
            Tag tag = accessTag.selectEntity(tagName);
            if(tag == null) continue;

            LinkedList<String> memoList = tag.getMemoList();
            memoList.add(m.getId());
            tag.setMemoList(memoList);
            accessTag.updateEntity(tag);
        }
    }

    /**
     * removes `m` from Tags by update TAG.memo_list to excluding `m.id`
     * used by updateEntity, deleteEntity
     */
    private void removeFromTags(Memo m){
        if(m == null) return;
        Iterator<String> tagNames = m.getTagList().iterator();

        while(tagNames.hasNext()){
            String tagName = tagNames.next();
            Tag tag = accessTag.selectEntity(tagName);
            if(tag == null) continue;

            LinkedList<String> memoList = tag.getMemoList();
            memoList.remove(m.getId());
            tag.setMemoList(memoList);
            accessTag.updateEntity(tag);
        }
    }
}
