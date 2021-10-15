<!DOCTYPE html>
<%@ page import = "com.rst.jsp_memo.model.*" %>
<%@ page import = "com.rst.jsp_memo.data.Memo" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.rst.jsp_memo.data.ReadWriteException" %>
<%
    String sessionId = request.getSession().getId();

    Iterator<String> tagList;
    try{
        tagList = Repository.getAllTags().iterator();
    }catch(ReadWriteException e){
        LinkedList<String> tmpList = new LinkedList<String>();
        tmpList.add(e.getMessage());
        tagList = tmpList.iterator();
    }
    

    TagDataModel model = (TagDataModel)Repository.get(sessionId);
    
    String TAG_NAME = model.getTagName();
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
        <% 
        while( tagList.hasNext() ){
            String tagName = tagList.next();
        %>
            <p class="tagName"><%= tagName %></p>
        <% } %>
    </header>
    <section class="container">
        <section class="sec1">
            <div class="newMemo">
                <div contenteditable="true" id="NewMemoTitle"></div>
                <div contenteditable="true" id="NewMemoContent"></div>
                <button id="NewMemoSave">save</button>
            </div>
        </section>
        <section class="sec2">
            <%
                Iterator<Memo> memoIt = memoList.iterator();
                while(memoIt.hasNext()){
                    Memo m = memoIt.next();
            %>
                <div class="memo" id="MEMO<%= m.getMemoId() %>">
                    <div contenteditable="true" class="memoTitle"><%= m.getTitle() %></div>
                    <div contenteditable="true" class="memoContent"><%= m.getContents() %></div>
                    <button class="memoModify" onClick="modifyMemo(<%= m.getMemoId() %>);">modify</button>
                </div>
            <%}%>
        </section>
    </section>

    <script src="/static/js/tag.js"></script>
</body>

</html>
