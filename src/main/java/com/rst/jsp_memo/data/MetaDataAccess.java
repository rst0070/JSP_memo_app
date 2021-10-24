package com.rst.jsp_memo.data;
import java.sql.*;

public class MetaDataAccess implements DataAccess<MetaData>{
    private static MetaDataAccess access = null;
    private Connection connection;
    private MetaDataAccess(){
        connection = DBConnection.getConnection();
    }

    public static MetaDataAccess getAccess(){
        if(access == null)  access = new MetaDataAccess();
        return access;
    }
    
    

    @Override
    public boolean isEntityExist(String name){
        boolean exist = false;
        try{
            PreparedStatement ps = connection.prepareStatement("select name from METADATA where name = ? ");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            exist = rs.next();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return exist;
    }

    @Override
    public MetaData selectEntity(String name){
        MetaData data = null;
        if(!isEntityExist(name)) return data;

        String sql =
        "select * from METADATA where name = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            rs.next();
            data = new MetaData();
            data.setName(name);
            data.setValue(rs.getString("value"));

            rs.close();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        return data;
    }
    
    @Override
    public void insertEntity(MetaData m){
        if(!m.checkValidation()) return;

        String sql = "insert into METADATA (name, value) "+
        "values (?, ?) ";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getName());
            ps.setString(2, m.getValue());
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    @Override
	public void updateEntity(MetaData m){
        if(!m.checkValidation()) return;

        String sql = "update METADATA "+
        "set value = ? "+
        "where name = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, m.getValue());
            ps.setString(2, m.getName());
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    @Override
	public void deleteEntity(String name){
        String sql = "delete from METADATA "+
        "where name = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            ps.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
