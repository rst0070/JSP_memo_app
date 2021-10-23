package com.rst.jsp_memo.data;

/**
 * entity라는걸 명시하기위한 인터페이스
 * 
 */
public interface Entity {
    
    /**
     * all get methods in Entity run this method for checking validation.<br/>
     * @return true: this Entity is valid. false: this Entity is not valid.
     */
    public boolean checkValidation();
}
