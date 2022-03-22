package com.rst.jsp_memo.controller;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.rst.jsp_memo.data.MetaData;
import com.rst.jsp_memo.service.PasswordService;

public class LoginAction extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		service = new PasswordService();
	 }
	static final long serialVersionUID = 1L;

	private PasswordService service;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, res);
	}

	/**
	 * 로그인 폼을 입력하고 post요청
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		String password = request.getParameter("pw");

		if(service.checkPassword(password)){
			HttpSession session = request.getSession();
			session.setAttribute("login", "true");
			response.sendRedirect("/memolist/");
		}else{
			doGet(request, response);
		}
	}
}
