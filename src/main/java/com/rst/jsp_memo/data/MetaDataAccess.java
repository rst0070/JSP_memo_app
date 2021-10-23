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
    
    public MetaData getByName(String name){
        MetaData data = null;
        String sql =
        "select * from METADATA where name = '?' ";
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

    }
    @Override
	public void updateEntity(MetaData m){

    }
    @Override
	public void deleteEntity(MetaData m){

    }
}
