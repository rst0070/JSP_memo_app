package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.rst.jsp_memo.Util;

@TestInstance(Lifecycle.PER_CLASS)
public class TagTest {

    private TagAccess access = TagAccess.getAccess();

    @BeforeEach
    public void init(){
        DBConnection.testingMode(true);
    }


    @Test
    @DisplayName("Test: TagAccess.selectAll()")
    public void selectAll(){
        Tag[] tag = new Tag[10];
        for(int i = 0; i < tag.length; i++){
            tag[i] = new Tag();
            tag[i].setName( (i+1)+"" );
            tag[i].setMemoList(Util.tokenStringToList("#1231#2342"));
            access.insertEntity(tag[i]);
        }

        LinkedList<Tag> list = access.selectAll();

        for(int i = 0; i < tag.length; i++)
            assertTrue(list.contains(tag[i]));
    }
    /**
     * 1. 아직 set을 하지않은 경우 invalid 상태이다.<br/>
     * 2. set을 하지 않았는데 get을 하면 null 반환한다.<br/>
     * 3. when all set is done, the Tag is valid.<br/>
     * 4. The valid tag could be inserted to DB by `TagAccess.insertEntity`<br/>
     * 5. when get 
     */
    @Test
    @DisplayName("Test: Tag object and TagAccess")
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

        //test delete
        access.deleteEntity(tag.getName());
        assertEquals(null, access.selectEntity(tag.getName()));
    }

}
