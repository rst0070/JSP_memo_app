package com.rst.jsp_memo.data;

public class MetaData implements Entity {
    private String name = null;
    private String value = null;

    @Override
    public boolean checkValidation(){
        if(name == null) return false;
        if(value == null) return false;
        return true;
    }

    public String getName(){
        if(name == null) return null;
        return new String(name);
    }

    public String getValue(){
        if(value == null)   return null;
        return new String(value);
    }

    public void setName(String name){
        if(name == null) this.name = null;
        else this.name = name;
    }

    public void setValue(String value){
        if(value == null) this.value = null;
        else this.value = value;
    }
}
