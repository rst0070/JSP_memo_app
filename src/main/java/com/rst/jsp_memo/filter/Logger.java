package com.rst.jsp_memo.filter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 애플리케이션에 들어오는 모든 요청에대한 로그작성.
 */
public class Logger implements Filter{
    
    @Override
    public void init(FilterConfig config){

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpReq = (HttpServletRequest)req;
        log(httpReq);
        chain.doFilter( req, res);
    }

    /**
     * 요청에 대한 로그남김.
     * @param req
     */
    private void log(HttpServletRequest req){
        HttpSession session = req.getSession();

        String sessionId = session.getId();
        String path = req.getRequestURI().toString();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println();

        System.out.println(dtf.format(now)+" - "+sessionId+": "+path);
    }

    @Override
    public void destroy(){

    }
}
