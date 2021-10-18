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
    private static final String DB_FILE = "jsp_memo.db";
    @BeforeAll
    public void init(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_FILE);
            System.out.println(connection.getSchema());
            statement = connection.createStatement();
            
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
        

    }

    @Test
    public void selectTest(){
        try{
            resultSet = statement.executeQuery("select * from `MEMO`");
            String result = resultSet.getNString("title");
            System.out.println(result);
            assertEquals("testpw", result);
        }catch(Exception e){
            e.printStackTrace();
        }
        

    }
}
