<%--
  Created by IntelliJ IDEA.
  User: bgzyy
  Date: 2019/7/7
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>历史信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".toPage").change(function () {
                var val = $(".toPage").val();
                window.location = "queryGpInfo.do?pageNum=" + val + "&gpCodeOrName=${gpCode}";
            });

            $(".first").click(function () {
                window.location = "queryGpInfo.do?pageNum=1&gpCodeOrName=${gpCode}";
            })

            $(".prev").click(function () {
                window.location = "queryGpInfo.do?pageNum=${pageInfo.prePage}&gpCodeOrName=${gpCode}";
            })

            $(".next").click(function () {
                window.location = "queryGpInfo.do?pageNum=${pageInfo.nextPage}&gpCodeOrName=${gpCode}";
            })
            $(".last").click(function () {
                window.location = "queryGpInfo.do?pageNum=${pageInfo.pages}&gpCodeOrName=${gpCode}";
            })
        })
    </script>
</head>
<body>
<div align="center">
    <h2>${gpName} 的历史价格信息表</h2>
    <table style="width: 40%;text-align: center;" cellpadding="6px">
        <tr>
            <th>日期</th>
            <th>股票名</th>
            <th>当日收盘价</th>
        </tr>
        <c:forEach items="${pageInfo.list}" var="allData">
            <tr>
                <td>${allData.date}</td>
                <td>${allData.gpName}</td>
                <td>${allData.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center" style="margin-top: 10px">
    <a href="getPageInfo.do?pageNum=1">返回主页</a>
    共 ${pageInfo.pages} 页&nbsp;&nbsp;
    <c:if test="${pageInfo.hasPreviousPage}">
        <span style="cursor: pointer;"
              class="first">首页</span>&nbsp;&nbsp;<span style="cursor: pointer;" class="prev">上一页</span>
    </c:if>
    &nbsp;&nbsp;第 ${pageInfo.pageNum} 页&nbsp;&nbsp;
    <c:if test="${pageInfo.hasNextPage}">
        <span style="cursor: pointer;"
              class="next">下一页</span>&nbsp;&nbsp;<span style="cursor: pointer;" class="last">尾页</span>&nbsp;&nbsp;
    </c:if>
    转到<input type="text" style="width: 20px;" class="toPage">页
</div>
</body>
</html>