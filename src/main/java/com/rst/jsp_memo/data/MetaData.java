package com.rst.jsp_memo.data;

public class MetaData implements Entity {
    private String name = null;
    private String value = null;

    @Override
    public boolean checkValidation(){
        boolean result = true;

        return result;
    }

    public String getName(){
        return new String(name);
    }

    public String getValue(){ 
        return new String(value);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setValue(String value){ 
        this.value = value;
    }
}
