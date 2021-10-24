package com.rst.jsp_memo.data;

/**
 * entity라는걸 명시하기위한 인터페이스
 * 
 */
public interface Entity {
    
    /**
     * if other methods in Entity use `checkValidation()` the code will be complex. <br/>
     * so, get or set methods in Entity should not use this method.<br/>
     * 
     * The problem not using `checkValidation` is null value.
     * all get methods check whethe value 
     * @return true: this Entity is valid. false: this Entity is not valid.
     */
    public boolean checkValidation();
}
