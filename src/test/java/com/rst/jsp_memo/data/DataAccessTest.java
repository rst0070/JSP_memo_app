package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class DataAccessTest {

    Connection connection;
    Statement statement;
	ResultSet resultSet;

    @BeforeAll
    public void init(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:");
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }catch(SqlException se){
            
        }
        

    }

    @Test
    public void selectTest(){

    }
}
