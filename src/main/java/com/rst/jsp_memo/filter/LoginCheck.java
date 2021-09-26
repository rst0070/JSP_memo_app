package com.rst.jsp_memo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class LoginCheck implements Filter {
	
	public void init(){
		
	}
	
	/**
	 * 로그인이 되지 않았다면 다시 로그인 페이지로 연결시킴.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

		//https://stackoverflow.com/questions/15009784/session-variables-in-servletrequest
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		Object isLogined = session.getAttribute("login");

		if(isLogined == null || "false".equals(isLogined)){
				((HttpServletResponse)response).sendRedirect("");
		}else{
			chain.doFilter(request, response);
		}
	}
	
	public void destroy(){
		
	}
}
