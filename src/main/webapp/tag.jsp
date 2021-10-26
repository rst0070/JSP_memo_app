<!DOCTYPE html>
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
    Iterator<Tag> tagIt = tagList.iterator();
    LinkedList<Memo> memoList = model.getMemoList();

%>
<html>
<head>
    <title>JSP_MEMO_<%= TAG_NAME %></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="/static/tag.css"/>
    <script>
        const TAG_NAME = "<%= TAG_NAME %>";
    </script>
</head>
<body>
    <header class="navBar">
        <p class="tagName">All Memos</p>
        <% 
        while( tagIt.hasNext() ){
            Tag t = tagIt.next();
        %>
            <p class="tagName"><%= t.getName() %></p>
        <% } %>
    </header>
    <section class="container">
        <section class="sec1">
            <div class="newMemo">
                <div contenteditable="true" id="NewMemoTitle"></div>
                <div contenteditable="true" id="NewMemoContent"></div>
                <button id="NewMemoTags">select tags</button>
                <button id="NewMemoSave">save</button>
            </div>
        </section>
        <section class="sec2">
            <%
                Iterator<Memo> memoIt = memoList.iterator();
                while(memoIt.hasNext()){
                    Memo m = memoIt.next();
            %>
                <div class="memo" id="MEMO<%= m.getId() %>">
                    <div contenteditable="true" class="memoTitle"><%= m.getTitle() %></div>
                    <div contenteditable="true" class="memoTags">
                        <% 
                        Iterator<String> tags = m.getTagList().iterator();
                        while( tags.hasNext() ){
                            out.print('#'+tags.next());
                        }
                        %>
                    </div>
                    <div contenteditable="true" class="memoContent"><%= m.getContent() %></div>
                    <button class="memoModify" onClick="modifyMemo(<%= m.getId() %>);">modify</button>
                </div>
            <%}%>
        </section>
    </section>
    <div class="selectTagPannel">

    </div>
    <script src="/static/js/tag.js"></script>
</body>

</html>
