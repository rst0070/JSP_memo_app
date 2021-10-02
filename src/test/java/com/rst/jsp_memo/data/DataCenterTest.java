package com.rst.jsp_memo.data;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.rst.jsp_memo.data.DataCenter;
import java.util.*;

public class DataCenterTest {
   
    @Test
    public void getAllTags(){
        LinkedList<String> tags = DataCenter.getAllTags();
        assertThat(tags.pop()).isEqualTo("test");
    }
}
