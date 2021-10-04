package com.rst.jsp_memo.data;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class DataCenterTest {
   
    @Test
    public void getAllTags() throws ReadWriteException{
        LinkedList<String> tags = DataCenter.getAllTags();
        assertThat(tags.pop()).isEqualTo("memo");
    }
}
