<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Admin Statistic</title>
    </head>
    <body>
        <h3> Statistic </h3>
        <h3>The number of messages sent in the application: </h3>
        ${messageStat}
        <h3>Online users: </h3>
        <c:forEach var = "user" items = "${onlineStat}" >
                ${user} <br>
            </c:forEach>
        <h3>Registered users in the application: </h3>
        <c:forEach items="${usersStat}" var="item">
            ${item} <br>
        </c:forEach>
        <form action="${ContextPath}/ui">
           <button>Main page</button>
        </form>
    </body>
</html>