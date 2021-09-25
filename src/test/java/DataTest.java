import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class DataTest{
	
	@Test
	public void password(){
		assertThat(com.rst.jsp_memo.data.MetaData.getLoginPassword()).isEqualTo("testpw");
	}
}