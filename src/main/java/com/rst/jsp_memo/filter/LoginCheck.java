package com.rst.jsp_memo.filter;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;

public class LoginCheck implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		
	}
	
	/**
	 * 로그인이 되지 않았다면 다시 로그인 페이지로 연결시킴.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

		//https://stackoverflow.com/questions/15009784/session-variables-in-servletrequest
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		boolean allowPass = passOrNot(req, session);

		if( allowPass )
			chain.doFilter( request, response );
		else
			((HttpServletResponse)response).sendRedirect("/login");
		
	}

	/**
	 * 사용자의 요청과 세션을 분석하여 강제로 로그인 페이지로 리디렉션할 것인지,
	 * 통과시킬것인지 결정한다.
	 * 
	 * 어떨때 통과시켜야하는가?
	 * 1. static 요청했을때
	 * 2. login page요청했을때
	 * 3. login 되어있을떄
	 * 
	 * @return - true: 통과시켜주기 false: 강제로 로그인페이지 리디렉션
	 * @param req - 판별할 사용자의 요청
	 * @param session - 판별할 사용자의 세션
	 */
	private boolean passOrNot(HttpServletRequest req, HttpSession session){
		Object isLogined = session.getAttribute("login");

		String path = req.getRequestURI().toString();

		if( path == null ) path = "/";


		if( path.startsWith("/static") ) return true;

		if( path.startsWith("/login") ) return true;

		if( isLogined != null && "true".equals(isLogined)) return true;

		return false;
	}
	
	@Override
	public void destroy(){
		
	}
}
