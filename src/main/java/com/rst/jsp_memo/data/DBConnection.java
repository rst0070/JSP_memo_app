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
}
