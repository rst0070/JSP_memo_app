package com.rst.jsp_memo.data;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RawDataTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("Nested Tests, testing RawData.readFile() method")
    class ReadFileCheck{

        @BeforeAll
        public void setting(){

        }

        @Test
        @DisplayName("is right exception occured?")
        public void exceptionTest(){
            // check error code.
            ReadWriteException e = assertThrows(ReadWriteException.class, () -> {
                RawData.readFile("/thisdoesntexist");
            });
            
            assertEquals(ReadWriteException.FILE_NOT_FOUND, e.error_code);   
        }

        @Test
        @DisplayName("read file correctly?")
        public void fileContentsTest(){
            try{
                String contents = RawData.readFile("/workspace/JSP_memo_app/data/login_pw.txt");
                assertEquals("testpw", contents);
            }catch(ReadWriteException e){
                e.printStackTrace();
            }
        }

    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("Nested Tests, testing RawData.writeFile() method")
    class WriteFileCheck{

        @BeforeAll
        public void setting(){

        }

        @Test
        @DisplayName("is right exception occured?")
        public void exceptionTest(){
            // check error code.
            ReadWriteException e = assertThrows(ReadWriteException.class, () -> {
                RawData.writeFile("/thisdoesntexist","");
            });
            
            assertEquals(ReadWriteException.FILE_NOT_FOUND, e.error_code);   
        }

    }

}
