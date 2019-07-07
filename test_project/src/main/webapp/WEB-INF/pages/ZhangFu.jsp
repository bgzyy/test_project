<%--
  Created by IntelliJ IDEA.
  User: bgzyy
  Date: 2019/7/7
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>历史涨幅信息</title>
</head>
<body>
<c:forEach items="${allZhangFuList}" var="goodZhangFuList">
    <c:forEach items="${goodZhangFuList}" var="goodZhangFu">
        ${goodZhangFu.allData.gpName} --> ${goodZhangFu.allData.date}<br>
    </c:forEach>
</c:forEach>
</body>
</html>
