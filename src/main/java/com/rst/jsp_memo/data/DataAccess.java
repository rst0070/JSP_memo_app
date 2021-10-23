package com.rst.jsp_memo.data;

/**
This is static factory class
aim to read text files.
data 패키지 내부의 클래스에서만 접근가능
*/ 
public interface DataAccess<E extends Entity> {

	public void insertEntity(E e);
	public void updateEntity(E e);
	public void deleteEntity(E e);

}
