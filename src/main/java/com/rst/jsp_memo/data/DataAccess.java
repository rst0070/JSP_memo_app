package com.rst.jsp_memo.data;

import java.sql.*;
import java.io.*;
/**
This is static factory class
aim to read text files.
data 패키지 내부의 클래스에서만 접근가능
*/ 
class DataAccess {
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

	public static Statement getStatement(){
		Statement st = null;
		try{
			st = connection.createStatement();
		}catch(SQLException se){
			se.printStackTrace();
		}
		return st; 
	}

	public static PreparedStatement getPreparedStatement(String query){
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(query);
		}catch(SQLException se){
			se.printStackTrace();
		}

		return ps;
	}
	public static ResultSet execute(String query){
		Statement st;
		ResultSet rs = null;
		try{
			st = connection.createStatement();
			rs = st.executeQuery(query);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return rs;
	}
	public static ResultSet execute(PreparedStatement query){
		ResultSet rs = null;
		try{
			rs = query.executeQuery();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return rs;
	}

	static class MetaData{
		final long LAST_MEMO_NUM;
		final String LOGIN_PW;
		public MetaData(long last_memo_num, String login_pw){
			LAST_MEMO_NUM = last_memo_num;
			LOGIN_PW = login_pw;
		}
	}

	public static MetaData getMetaData(){
		ResultSet rs = execute("select * from METADATA");
		MetaData md = null;
		try{
			rs.next();
			md = new MetaData( rs.getLong("last_memo_num"), rs.getString("login_pw"));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return md;
	}

}
