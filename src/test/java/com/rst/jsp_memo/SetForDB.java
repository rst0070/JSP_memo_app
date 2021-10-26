package com.rst.jsp_memo;

import com.rst.jsp_memo.data.*;

import org.junit.jupiter.api.Test;

/**
 * This is 
 * 
 */
public class SetForDB {
    
    @Test
    public void setting(){
        DBConnection.testingMode(true);
        DBConnection.testingMode(false);

        MetaDataAccess mda = MetaDataAccess.getAccess();
        MetaData pwMD = new MetaData();
        pwMD.setName("password");
        pwMD.setValue("testpw");
        mda.insertEntity(pwMD);
    }
}
