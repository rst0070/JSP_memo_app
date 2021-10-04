package com.rst.jsp_memo.controller;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rst.jsp_memo.data.DataCenter;
import com.rst.jsp_memo.data.ReadWriteException;
public class LoginAction extends HttpServlet{
	
	static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String password = request.getParameter("pw");

		//패스워드 읽는중 예외발생시 예외내용 사용자에게 전달.
		String realPassword = "";
		try{
			realPassword = DataCenter.getLoginPassword();
		}catch(ReadWriteException e){
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			String message = e.getMessage();

			bw.write(e.getMessage(), 0, message.length());
			bw.flush();
			return;
		}
		
		//예외 발생하지 않을시 session 작업
		if(password.equals(realPassword)){
			HttpSession session = request.getSession();
			session.setAttribute("login", "true");
			//response.sendRedirect("/index.jsp");
		}else{
			response.sendRedirect("/login.jsp");
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet( request, response );
	}
}
