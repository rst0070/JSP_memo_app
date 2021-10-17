# JSP Memo Application
SQLite를 이용한 메모 애플리케이션

# 데이터관리는 어떻게 하는가?
[SQLite online](https://sqliteonline.com/)  
1. 데이터 리소스는 sqlite를 이용한다.(text 파일을 이용하니 io에서 신경 쓸 부분이 너무 많음)
2. 애플리케이션 내부에서 데이터에 접근하거나 수정하는 동작이 하나의 클래스를 통해서만 
이루어지도록 구현했다.(static factory) `com.rst.jsp_memo.data.DataCenter`

# MVC모델 구현방법
1. 로그인 페이지 같은 간단한 페이지가 아닌 이상 servlet에서 request를 받는다.
2. servlet은 com.rst.jsp_memo.model패키지를 활용하여 데이터를 저장한다.
3. servlet에서 데이터 저장후 response.redirect를 이용해 jsp를 호출한다.
4. jsp에서 com.rst.jsp_memo.model에 접근하여 데이터를 이용한다.

### reference
* [maven](https://youtube.com/playlist?list=PL92E89440B7BFD0F6)
* [jsp & servlet](https://youtube.com/playlist?list=PLE0F6C1917A427E96)
* [jetty](https://www.eclipse.org/jetty/documentation/jetty-9/index.html)  
