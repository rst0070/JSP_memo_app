package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private final String[] TAG_NAME = {"tag1", "tag2", "tag3", "tag4"};

    @BeforeEach
    public void setting(){
        DBConnection.testingMode(true);
        Tag[] tag = new Tag[TAG_NAME.length];
        for(int i = 0; i < tag.length; i++){
            tag[i] = new Tag();
            tag[i].setName(TAG_NAME[i]);
            tag[i].setMemoList(new LinkedList<String>());
            accessTag.insertEntity(tag[i]);
        }
    }
    
    @Test
    public void getSetTest(){
        Memo m = new Memo();
        m.setId();
        m.setTitle("test title");
        m.setContent("asdasdasda");
        m.setTagList(Util.tokenStringToList('#'+TAG_NAME[0]+'#'+TAG_NAME[2]));

        assertEquals("test title", m.getTitle());
        assertEquals("asdasdasda", m.getContent());
        assertEquals(true, m.getTagList().contains(TAG_NAME[0]));

        assertEquals(true, m.checkValidation());//the tag is on db.
    }

    @Test
    public void selectAll(){
        Memo[] memo = new Memo[10];
        for(int i = 0; i < memo.length; i++){
            memo[i] = new Memo();
            memo[i].setContent("sdfsfsdfsdf");
            memo[i].setId();
            memo[i].setTagList(Util.tokenStringToList('#'+TAG_NAME[0]));
            memo[i].setTitle(((i+1)*(i+1))+"");
            access.insertEntity(memo[i]);
        }
        LinkedList<Memo> getList = access.selectAll();
        for(int i = 0; i < memo.length; i++)
            assertTrue(getList.contains(memo[i]));
    }

    @Test
    public void insert(){
        Memo m = new Memo();
        m.setContent("asdasd");
        m.setId();
        m.setTagList(Util.tokenStringToList('#'+TAG_NAME[0]+'#'+TAG_NAME[2]));
        m.setTitle("new title");
        access.insertEntity(m);

        Memo mGet = access.selectEntity(m.getId());
        assertEquals(m.getId(), mGet.getId());
        assertEquals(m.getTagList(), mGet.getTagList());

        //insertEntity should call addToTag method.
        Tag tag = accessTag.selectEntity(TAG_NAME[0]);
        assertEquals(true, tag.getMemoList().contains(m.getId()));
    }

    @Test
    public void update(){
        Memo m = new Memo();
        m.setContent("asdasd");
        m.setId();
        m.setTagList(Util.tokenStringToList('#'+TAG_NAME[0]+'#'+TAG_NAME[2]));
        m.setTitle("new title");
        access.insertEntity(m);


        m.setContent("this is modifyied");
        m.setTagList(Util.tokenStringToList('#'+TAG_NAME[1]));
        access.updateEntity(m);

        Memo mGet = access.selectEntity(m.getId());
        assertEquals(m.getContent(), mGet.getContent());

        Tag tag = accessTag.selectEntity(TAG_NAME[1]);
        assertEquals(true, tag.getMemoList().contains(m.getId()));
        tag = accessTag.selectEntity(TAG_NAME[0]);
        assertEquals(false, tag.getMemoList().contains(m.getId()));
    }

    /**
     * need to check the tag that including memo changed by delete memo.
     */
    @Test
    public void delete(){
        Memo m = new Memo();
        m.setContent("asdasd");
        m.setId();
        m.setTagList(Util.tokenStringToList('#'+TAG_NAME[0]+'#'+TAG_NAME[2]));
        m.setTitle("new title");
        access.insertEntity(m);

        access.deleteEntity(m.getId());
        assertEquals(false, access.isEntityExist(m.getId()));

        Tag tag = accessTag.selectEntity(TAG_NAME[0]);
        assertEquals(false, tag.getMemoList().contains(m.getId()));
        tag = accessTag.selectEntity(TAG_NAME[2]);
        assertEquals(false, tag.getMemoList().contains(m.getId()));
    }

}