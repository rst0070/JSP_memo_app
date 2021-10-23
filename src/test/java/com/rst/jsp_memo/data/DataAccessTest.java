package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

@TestInstance(Lifecycle.PER_CLASS)
public class DataAccessTest {

    /**
     * 1. 아직 set을 하지않은 경우 invalid 상태이다.<br/>
     * 2. set을 하지 않았는데 get을 하면 null 반환한다.<br/>
     * 3. when all set is done, the Tag is valid.<br/>
     * 4. The valid tag could be inserted to DB by `TagAccess.insertEntity`<br/>
     * 5. when get 
     */
    @Test
    @DisplayName("Test: Tag object")
    public void tagTest(){
        
        Tag tag = new Tag();
        assertEquals(false, tag.checkValidation());
        assertEquals(null, tag.getName());
        assertEquals(null, tag.getMemoList());

        tag.setName("testName");
        

        LinkedList<String> list = new LinkedList<String>();
        list.add("memo1");
        list.add("memo2");
        tag.setMemoList(list);

        assertEquals(true, tag.checkValidation());
        assertEquals("testName", tag.getName());

        TagAccess access = TagAccess.getAccess();
       

        //test insertEntity
        access.insertEntity(tag);
        Tag tag2 = access.selectEntity(tag.getName());
        assertEquals(tag.getName(), tag2.getName());
        assertEquals(tag.getMemoList(), tag2.getMemoList());

        //test updateEntity
        list.add("memo3");
        tag.setMemoList(list);
        access.updateEntity(tag);
        tag2 = access.selectEntity(tag.getName());
        assertEquals(tag.getMemoList(), tag2.getMemoList());
    }

    @Test
    @DisplayName("Test: Memo and MemoAccess")
    public void memoTest(){
        
        Memo memo = new Memo();
        memo.setId();
        memo.setTitle("test title");

        LinkedList<String> list = new LinkedList<String>();
        list.add("thisIsNotExistingTagName");
        memo.setTagList(list);
        memo.setContent("this is test content");

        assertEquals(false, memo.checkValidation());//because the memo has inExisting Tag.

        list.poll();
        list.add("testName");
    }
}
