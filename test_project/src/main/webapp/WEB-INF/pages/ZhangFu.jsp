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
<div align="center">
    <h2>股票前三十天涨幅超过百分之五的次数</h2>
    <a href="getPageInfo.do?pageNum=1">返回主页</a><br><br>
    <c:forEach items="${pageList}" var="page" varStatus="status">
        ${page.name}->${page.count} 次&nbsp;
        <c:if test="${status.count % 6 == 0}">
            <br><br/>
        </c:if>
    </c:forEach>
</div>
</body>
</html>
