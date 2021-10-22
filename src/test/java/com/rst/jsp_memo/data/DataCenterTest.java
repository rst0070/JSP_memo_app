package com.rst.jsp_memo.data;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.*;

public class DataCenterTest {
   
    @Test
    public void getAllTags(){
        LinkedList<String> tags = DataCenter.getAllTags();
        assertThat(tags.pop()).isEqualTo("memo");
    }

    @Test
    public void getMemo(){
        long memoId = 1;

        Memo m = DataCenter.getMemo(memoId);
        assertEquals("this is test memo", m.getTitle());
        assertEquals("contents......\n", m.getContents());
    }
}
