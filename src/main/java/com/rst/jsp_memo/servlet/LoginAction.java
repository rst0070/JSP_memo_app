package com.rst.jsp_memo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends HttpServlet{
	
	static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try{
			String password = request.getParameter("pw");
		}catch(Exception e){
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet( request, response );
	}
}
