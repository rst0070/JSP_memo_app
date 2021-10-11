<!DOCTYPE html>
<%@ page import = "com.rst.jsp_memo.model.*" %>
<%@ page import = "com.rst.jsp_memo.data.Memo" %>
<%@ page import = "java.util.*" %>
<%
    String sessionId = request.getId();

    TagDataModel model = (TagDataModel)Repository.get(sessionId);
    String TAG_NAME = model.getTagName();
    LinkedList<Memo> memoList = model.getMemoList();

%>
<html>
<head>
    <title>JSP_MEMO_<%= TAG_NAME %></title>
</head>
<body>
    <div class="menu_pannel">

    </div>
    <div>

    </div>
</body>

</html>
