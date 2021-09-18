package com.rst.jsp_memo.servlet;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = {"/main"})
public class MainPage extends HttpServlet{
    
    static final long SerialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter pw = response.getWriter();
        pw.print("<h1>Hello world!</h1>");
        System.out.println("connected");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        
    }
}
