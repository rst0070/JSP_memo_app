package com.rst.jsp_memo.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rst.jsp_memo.data.DataCenter;
public class LoginAction extends HttpServlet{
	
	static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String password = request.getParameter("pw");

		if(password.equals(DataCenter.getLoginPassword())){
			HttpSession session = request.getSession();
			session.setAttribute("login", "true");
			response.sendRedirect("/index.jsp");
		}else{
			response.sendRedirect("/login.jsp");
		}	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet( request, response );
	}
}
