# JSP Memo Application
텍스트 파일 입출력을 이용한 Memo Application

# 데이터관리는 어떻게 하는가?
1. 데이터 리소스는 특정폴더에 지정을 한다.
2. 데이터에 직접접근하는 유일한 메소드들을 모아두었다.(static factory) com.rst.jsp_memo.data.RawData
3. 애플리케이션 내부에서 데이터에 접근하거나 수정하는 동작이 하나의 클래스를 통해서만 
이루어지도록 구현했다.(static factory) com.rst.jsp_memo.data.DataCenter
4. 한번 열었거나 만든 메모는 static HashMap에 Memo객체로 저장되며 애플리케이션 내부에 어느곳에서든 접근할 수 있다.
이는 com.rst.jsp_memo.data.MemoRepository static factory가 구현하며 
이를통해 controller에서 view로 데이터를 전송하는것이 가능하다. 이때 애플리케이션에서는 MemoRepository로 접근하지않고 
DataCenter를 이용해서 메모에 접근한다.
5. DataCenter는 아직 읽지 않은 메모는 local에서 불러오고 읽은 메모는 MemoRepository에서 가져온다. 또 메모를 local에 저장하기도한다. 

# MVC모델 구현방법
1. 로그인 페이지 같은 간단한 페이지가 아닌 이상 servlet에서 request를 받는다.
2. servlet은 com.rst.jsp_memo.model패키지를 활용하여 데이터를 저장한다.
3. servlet에서 데이터 저장후 response.redirect를 이용해 jsp를 호출한다.
4. jsp에서 com.rst.jsp_memo.model에 접근하여 데이터를 이용한다.

### reference
* [maven](https://youtube.com/playlist?list=PL92E89440B7BFD0F6)
* [jsp & servlet](https://youtube.com/playlist?list=PLE0F6C1917A427E96)
* [jetty](https://www.eclipse.org/jetty/documentation/jetty-9/index.html)  
