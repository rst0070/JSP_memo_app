package com.rst.jsp_memo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.rst.jsp_memo.data.*;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * This is 
 * 
 */

 @TestInstance(Lifecycle.PER_CLASS)
public class SetForDB {
    MetaDataAccess mda;
    TagAccess ta;
    MemoAccess ma;
    @BeforeAll
    public void setting(){
        DBConnection.testingMode(true);
        DBConnection.testingMode(false);
        mda = MetaDataAccess.getAccess();
        ta = TagAccess.getAccess();
        ma = MemoAccess.getAccess();

        MetaData pwMD = new MetaData();
        pwMD.setName("password");
        pwMD.setValue("testpw");
        mda.insertEntity(pwMD);

        Tag[] tags = new Tag[2];
        for(int i = 0; i < tags.length; i++){
            tags[i] = new Tag();
            tags[i].setName("exTag"+(i+1));
            tags[i].setMemoList(new LinkedList<String>());
            ta.insertEntity(tags[i]);
        }

        Memo[] memos = new Memo[10];
        for(int i = 0; i < memos.length; i++){
            memos[i] = new Memo();
            memos[1].setId();
            memos[i].setTitle("example Title "+(i+1));
            String content = "this is example content";
            String tagText = "";
            for(int j = 0; j < i; j++){
                content = content + '\n' + content;
                tagText += '#' + tags[j].getName();
            }
            
            memos[i].setContent(content);
            memos[i].setTagList(Util.tokenStringToList(tagText));
            ma.insertEntity(memos[i]);
        }

    }

    @ParameterizedTest
    @DisplayName("")
    @ValueSource(strings = {"password#testpw"})
    public void testMetaData(String str){
        StringTokenizer st = new StringTokenizer(str, "#");
        String name = st.nextToken();
        String value = st.nextToken();
        MetaData m = mda.selectEntity(name);
        assertEquals(value, m.getValue());
    }
}
