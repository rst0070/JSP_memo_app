package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

@TestInstance(Lifecycle.PER_CLASS)
public class MetaDataTest {
    
    private MetaDataAccess access = MetaDataAccess.getAccess();

    @BeforeEach
    public void clean(){
        DBConnection.testingMode(true);
    }

    @Test
    public void getSetTest(){
        MetaData md = new MetaData();
        md.setName("password");
        md.setValue("testpw");

        assertEquals(true, md.checkValidation());

        md.setName(null);
        assertEquals(null, md.getName());

        md.setValue(null);
        assertEquals(null, md.getValue());

        assertEquals(false, md.checkValidation());
    }


    @Test
    public void selectAll(){
        MetaData[] data = new MetaData[10];
        for(int i = 0; i < data.length; i++){
            data[i] = new MetaData();
            data[i].setName((i+1)+"");
            data[i].setValue(((i+1)*(i+1))+"");
            access.insertEntity(data[i]);
        }

        LinkedList<MetaData> list = access.selectAll();
        
        for(int i = 0; i < data.length; i++)
            assertEquals(true, list.contains(data[i]));
    }

    @Test
    public void insert(){
        MetaData md = new MetaData();
        md.setName("password");
        md.setValue("testpw");

        access.insertEntity(md);
        MetaData mdGet = access.selectEntity(md.getName());
        assertEquals(md.getValue(), mdGet.getValue());
    }

    @Test
    public void update(){
        MetaData md = new MetaData();
        md.setName("password");
        md.setValue("testpw");

        access.insertEntity(md);
        
        md.setValue("newpw");
        access.updateEntity(md);

        MetaData mget = access.selectEntity(md.getName());
        assertEquals(md.getValue(), mget.getValue());
    }

    @Test
    public void delete(){
        MetaData md = new MetaData();
        md.setName("password");
        md.setValue("testpw");

        access.insertEntity(md);
        
        access.deleteEntity(md.getName());

        assertEquals(false, access.isEntityExist(md.getName()));
    }
}
