package data;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.rst.jsp_memo.data.RawData;

public class RawDataTest {
    
    
    @Test
    public void readFile(){
        String contents = RawData.readFile("/workspace/JSP_memo_app/data/login_pw.txt");
        assertThat(contents).isEqualTo("testpw");
    }
}
