package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.rst.jsp_memo.Util;
import java.util.*;
/**
 * 1. memo obj
 * 2. MemoAccess
 * 
 */
@TestInstance(Lifecycle.PER_CLASS)
public class MemoTest{

    private MemoAccess access = MemoAccess.getAccess();
    private TagAccess accessTag = TagAccess.getAccess();

    private Memo m;
    private Tag t1, t2;
    private LinkedList<String> tags;

    @BeforeEach
    public void setting(){
        DBConnection.testingMode(true);
        m = new Memo();
        tags = Util.tokenStringToList("#testTag1#testTag2");

        m.setTagList(tags);
        m.setTitle("test title");
        m.setContent("asdasdasda");
        m.setId();

        t1 = new Tag();
        t2 = new Tag();
        t1.setMemoList(Util.tokenStringToList('#'+m.getId()));
        t1.setName("testTag1");
        t2.setMemoList(Util.tokenStringToList('#'+m.getId()));
        t2.setName("testTag2");
        

        accessTag.insertEntity(t1);
        accessTag.insertEntity(t2);
    }
    
    @Test
    public void getSetTest(){

        assertEquals("test title", m.getTitle());
        assertEquals("asdasdasda", m.getContent());
        assertEquals(tags, m.getTagList());

        assertEquals(true, m.checkValidation());//the tag is on db.
        assertEquals(m.getId(), t1.getMemoList().peek());
    }

    @Test
    public void selectAll(){
        access.insertEntity(m);
        Memo m2 = new Memo();
        m2.setId();
        m2.setTitle("second memo");
        m2.setTagList(Util.tokenStringToList("#testTag1"));
        m2.setContent("sdfsdff");

        access.insertEntity(m2);

        LinkedList<Memo> getList = access.selectAll();
        assertEquals(true, getList.contains(m));
        assertEquals(true, getList.contains(m2));
    }

    @Test
    public void insert(){
        access.insertEntity(m);
        Memo mGet = access.selectEntity(m.getId());
        assertEquals(m.getId(), mGet.getId());
        assertEquals(m.getTagList(), mGet.getTagList());

        //insertEntity should call addToTag method.
        Tag tag = accessTag.selectEntity("testTag1");
        assertEquals(true, tag.getMemoList().contains(m.getId()));
    }

    @Test
    public void update(){
        access.insertEntity(m);
        m.setContent("this is modifyied");
        m.setTagList(Util.tokenStringToList("#testTag1"));
        access.updateEntity(m);
        Memo mGet = access.selectEntity(m.getId());
        assertEquals(m.getContent(), mGet.getContent());

        Tag tag = accessTag.selectEntity("testTag2");
        assertEquals(false, tag.getMemoList().contains(m.getId()));
    }

    /**
     * need to check the tag that including memo changed by delete memo.
     */
    @Test
    public void delete(){
        access.insertEntity(m);
        access.deleteEntity(m.getId());
        assertEquals(false, access.isEntityExist(m.getId()));

        Tag tag = accessTag.selectEntity("testTag2");
        assertEquals(false, tag.getMemoList().contains(m.getId()));
        tag = accessTag.selectEntity("testTag1");
        assertEquals(false, tag.getMemoList().contains(m.getId()));
    }

}