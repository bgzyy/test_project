<%--
  Created by IntelliJ IDEA.
  User: bgzyy
  Date: 2019/7/6
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".toPage").change(function () {
                var val = $(".toPage").val();
                window.location = "getPageInfo.do?pageNum=" + val;
            });
            $(".first").click(function () {
                window.location = "getPageInfo.do?pageNum=${pageInfo.firstPage}";
            })

            $(".prev").click(function () {
                window.location = "getPageInfo.do?pageNum=${pageInfo.prePage}";
            })

            $(".next").click(function () {
                window.location = "getPageInfo.do?pageNum=${pageInfo.nextPage}";
            })
            $(".last").click(function () {
                window.location = "getPageInfo.do?pageNum=${pageInfo.pages}";
            })
        })
    </script>
</head>
<body>
<form method="post" action="queryGpInfo.do" style="margin-left:9%">
    <input type="text" name="gpCodeOrName" placeholder="请输入股票代码或股票名"/>
    <input type="hidden" name="pageNum" value="1">
    <button type="submit">查询</button>
</form>
<a href="getAllZhangFuInfo.do" style="margin-left:9%">查看历史涨幅信息</a><br><br>
<div align="center">
    <c:forEach items="${pageInfo.list}" var="gpBean" varStatus="status">
        <a href="queryGpInfo.do?pageNum=1&gpCodeOrName=${gpBean.gpCode}" style="margin: 30px">${gpBean.gpName}</a>
        <c:if test="${status.count%6 == 0}">
            <br><br>
        </c:if>
    </c:forEach>
</div>
<div align="center" style="margin-top: 10px">
    共 ${pageInfo.pages} 页&nbsp;&nbsp;
    <c:if test="${pageInfo.hasPreviousPage}">
        <span style="cursor: pointer;" class="first">首页</span>&nbsp;&nbsp;<span style="cursor: pointer;" class="prev">上一页</span>
    </c:if>
    &nbsp;&nbsp;第 ${pageInfo.pageNum} 页&nbsp;&nbsp;
    <c:if test="${pageInfo.hasNextPage}">
        <span style="cursor: pointer;" class="next">下一页</span>&nbsp;&nbsp;<span style="cursor: pointer;" class="last">尾页</span>&nbsp;&nbsp;
    </c:if>
    转到<input type="text" style="width: 20px;" class="toPage">页
</div>
</body>
</html>