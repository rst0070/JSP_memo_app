package com.rst.jsp_memo.controller;

import com.rst.jsp_memo.service.PasswordService;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet {

    @Override
    public void init(){
        service = new PasswordService();
    }

    private PasswordService service;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.sendRedirect("/login");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String pw = req.getParameter("pw");
        String newPw = req.getParameter("new_pw");

        if(service.checkPassword(pw))
            service.changePassword(newPw);

        res.sendRedirect("/memolist");
    }
}
