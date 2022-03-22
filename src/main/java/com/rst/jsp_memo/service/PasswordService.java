package com.rst.jsp_memo.service;

import com.rst.jsp_memo.data.DBConnection;
import com.rst.jsp_memo.data.MetaData;
import com.rst.jsp_memo.data.MetaDataAccess;

public class PasswordService {

    public PasswordService(){
        DBConnection.testingMode(false);
        access = MetaDataAccess.getAccess();
    }

    private MetaDataAccess access;

    /**
     * 문자열이 패스워드와 일치하는지 확인
     * @param passwordStr - 확인하고자하는 문자열
     * @return True - 문자열이 DB의 password와 일치할 시. 아님 False
     */
    public boolean checkPassword(String passwordStr){
        MetaData data = access.selectEntity("password");
        return data.getValue().equals(passwordStr);
    }

    public void changePassword(String newPassword){
        access.updateEntity(new MetaData("password", newPassword));
    }
}
