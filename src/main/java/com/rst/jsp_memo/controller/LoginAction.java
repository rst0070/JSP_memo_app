package com.rst.jsp_memo.controller;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rst.jsp_memo.data.MetaDataAccess;
import com.rst.jsp_memo.data.MetaData;

public class LoginAction extends HttpServlet{
	
	static final long serialVersionUID = 1L;
	
	private MetaDataAccess access = MetaDataAccess.getAccess();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String password = request.getParameter("pw");

		//패스워드 읽는중 예외발생시 예외내용 리스폰스로 사용자에게 전달.
		String realPassword = "";

		MetaData data = access.selectEntity("password");
		realPassword = data.getValue();
		
		
		//예외 발생하지 않을시 session 작업
		//main page로 redirect한다.
		if(password != null && realPassword != null && password.equals(realPassword)){
			HttpSession session = request.getSession();
			session.setAttribute("login", "true");
			response.sendRedirect("/tag/memo");
		}else{
			response.sendRedirect("/login.jsp");
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet( request, response );
	}
}
