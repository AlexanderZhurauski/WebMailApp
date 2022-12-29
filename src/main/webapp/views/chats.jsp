<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="dto.MessageRecipientDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="services.api.IMessageService" %>
<%@ page import="services.ServiceProvider" %>

<%@ include file="getlogin.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8"%>

<%! List<MessageRecipientDTO> messageList; %>
<% IMessageService service = ServiceProvider.getInstance()
        .getMessageService();
    messageList = service.get(getLogin(session));
    session.setAttribute("messageList", messageList);
    %>

<html>
<head>
    <title>ReceivedMessages</title>
</head>
<body>
    <h2>User <%= getLogin(session)%>'s Mailbox: Received Messages</h2>

    <c:if test="${messageList.size() == 0}">
        <h2>The mailbox is empty!</h2>
    </c:if>

    <c:forEach var = "message" items = "${messageList}" >
        ${message.time}, ${message.sender}: <br>
        ${message.text} <br> <hr>
    </c:forEach>
</body>
</html>

