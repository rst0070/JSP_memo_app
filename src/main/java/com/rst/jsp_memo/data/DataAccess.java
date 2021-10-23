package com.rst.jsp_memo.data;

/**
This is static factory class
aim to read text files.
data 패키지 내부의 클래스에서만 접근가능
*/ 
public interface DataAccess<E extends Entity> {

	public boolean isEntityExist(String key);

	/**
	 * This method should check that entity corresponds the key is existing, before query to DB.<br/>
	 * In that situation this method calls `isEntityExist` method.<br/>
	 * @param	primary key for the Entity
	 * @return E - when there is Entity corresponds the `key`, null - error occured or invalid key
	 */
	public E selectEntity(String key);
	public void insertEntity(E e);
	public void updateEntity(E e);
	public void deleteEntity(String key);

}
