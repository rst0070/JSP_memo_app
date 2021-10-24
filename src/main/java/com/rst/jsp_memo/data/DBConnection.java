package com.rst.jsp_memo.data;

import java.sql.*;

public class DBConnection {
    private static Connection connection;
	private static final String DB_FILE = "jsp_memo.db";

	static{
		try {
		// SQLite JDBC 체크
		Class.forName("org.sqlite.JDBC");
		
		connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
		connection.setAutoCommit(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    protected static Connection getConnection(){
        return connection;
    }

	/**
	 * The Testing Mode removes all data from DataBase,
	 * and turn off auto commit.<br/>
	 * !IMPORTANT: removed data will be not backed up.
	 * 
	 * It is good to run this method after all test.
	 * @param on - true: turn on Testing Mode. false: turn off Testing Mode.
	 */
	public static void testingMode(boolean on){
		try{
			if(on){
				connection.setAutoCommit(false);
				Statement st = connection.createStatement();
				st.execute("delete from TAG");
				st.execute("delete from MEMO");
				st.execute("delete from METADATA");
			}else connection.setAutoCommit(true);
		}catch(SQLException se){
			se.printStackTrace();
		}
		
	}
}
