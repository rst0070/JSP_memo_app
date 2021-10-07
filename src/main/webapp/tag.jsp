<!DOCTYPE html>
<% 
import com.rst.jsp_memo.model.*;
import com.rst.jsp_memo.data.Memo;
import java.util.*;
%>
<html>
<head>

</head>
<body>
    <%
        try{

        }
        String sessionId = request.getId();

        TagDataModel model = (TagDataModel)Repository.get(sessionId);
        String TAG_NAME = model.getTagName();
        LinkedList<Memo> memoList = model.getMemoList();

    %>

    <h1><%= TAG_NAME %></h1>
    <% for(Memo m : memoList ){ %>
        <p><%= m.getTitle() %></p>
    <% } %>
</body>

</html>
