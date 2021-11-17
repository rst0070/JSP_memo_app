package com.rst.jsp_memo.controller;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.rst.jsp_memo.data.MetaDataAccess;
import com.rst.jsp_memo.data.MetaData;
import com.rst.jsp_memo.data.DBConnection;

public class LoginAction extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		DBConnection.testingMode(false);
	 }
	static final long serialVersionUID = 1L;
	
	private MetaDataAccess access = MetaDataAccess.getAccess();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String password = request.getParameter("pw");
		String realPassword = "";

		MetaData data = access.selectEntity("password");
		realPassword = data.getValue();
		
		
		if(password != null && realPassword != null && password.equals(realPassword)){
			//예외 발생하지않고 패스워드 맞을때
			String path = request.getRequestURI().toString();
			if(path == null) path = "/";
			if(path.equals("/login/change")){//password 변경 요청
				
				MetaData newPw = new MetaData();
				newPw.setName("password");
				newPw.setValue(request.getParameter("new_pw"));
				System.out.println("new password:" + newPw.getValue());
				access.updateEntity(newPw);
				response.sendRedirect("/login.jsp");
			}else{// login요청
				HttpSession session = request.getSession();
				session.setAttribute("login", "true");
				response.sendRedirect("/memolist/");
			}
		}else{//예외 or 패스워드 틀릴때
			response.sendRedirect("/login.jsp");
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet( request, response );
	}
}
