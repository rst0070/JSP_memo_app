package com.rst.jsp_memo.controller;

import javax.servlet.http.*;
import java.io.*;
public class Logout extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        HttpSession session = req.getSession();
        session.setAttribute("login", "false");
        res.sendRedirect("/login.jsp");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        doGet(req, res);
    }
}
