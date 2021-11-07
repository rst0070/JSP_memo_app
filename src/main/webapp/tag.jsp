<%@ page import = "com.rst.jsp_memo.model.*" %>
<%@ page import = "com.rst.jsp_memo.data.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%
/**
* 만약 사용자가 controller를 거치지 않고 바로 jsp를 요청했다면..
* 다시 controller로 redirect 해야한다.
*/
String isVisitedController = session.getAttribute("visitedController").toString();
if( isVisitedController != null && isVisitedController.equals("true") ){
   session.setAttribute("visitedController", "false");
}
else{
   response.sendRedirect("/tag");
   return;
}

String sessionId = session.getId();
TagDataModel model = (TagDataModel)Repository.get(sessionId);

String TAG_NAME = model.getTagName();
LinkedList<Tag> tagList = model.getAllTags();
LinkedList<Memo> memoList = model.getMemoList();

%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP_MEMO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="/static/css/tag/main.css"/>
    <link type="text/css" rel   ="stylesheet" href="/static/css/tag/memo.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/tag/navbar.css"/>
    <script type="module" src="/static/js/tag/main.js"></script>
    <script>
        const TAG_NAME = "<%= TAG_NAME %>";
    </script>
</head>
<body>
    <header class="navBar">
        <p><a href="/memolist/">All Memos</a></p>
        <% while(!tagList.isEmpty()){ 
             String tName = tagList.poll().getName();    
        %>
            <p class="tagName" value="<%= tName %>"><a href="/memolist/<%= tName %>"><%= tName %></a></p>
        <% } %>
    </header>
    <section class="container">
        <section class="sec1">
            <div class="newMemo">
                <button id="NewMemoButton">open new memo</button>
            </div>
        </section>
        <section class="sec2">
            <%
            while(!memoList.isEmpty()){
                Memo memo = memoList.poll();
                LinkedList<String> tagListOfMemo = memo.getTagList();
            %>
            <div class="memo" memoid="<%= memo.getId() %>">
                <div class="memoTitle"><%= memo.getTitle() %></div>
                <div class="memoTags">
                    <%
                    while(!tagListOfMemo.isEmpty()){
                        String tName = tagListOfMemo.poll();
                    %>
                        <span class="tag" value="<%= tName %>">#<%= tName %></span>
                    <% } %>
                </div>
                <pre class="memoContent"><%= memo.getContent() %></pre>
                <button class="memoModify">modify</button>
            </div>
            <% } %>
        </section>
    </section>
    <div id="selectTagContainer" style="display:none;"></div>
    <div id="memoEditContainer" style="display: none;"></div>
</body>

</html>