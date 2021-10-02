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
            //ParameterizedTest로 사용해야함..!
            /*
            try{
                String contents = RawData.readFile("");
            }catch(ReadWriteException e){

            }*/
        }

    }

}
